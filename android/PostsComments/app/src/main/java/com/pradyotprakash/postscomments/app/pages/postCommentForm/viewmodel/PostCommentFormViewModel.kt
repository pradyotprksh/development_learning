package com.pradyotprakash.postscomments.app.pages.postCommentForm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradyotprakash.postscomments.core.navigator.Navigator
import com.pradyotprakash.postscomments.core.response.PostsCommentsResponse
import com.pradyotprakash.postscomments.core.utils.PostCommentFormArguments
import com.pradyotprakash.postscomments.domain.usecases.CommentUseCase
import com.pradyotprakash.postscomments.domain.usecases.PostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostCommentFormViewModel @Inject constructor(
    private val navigator: Navigator,
    private val postUseCase: PostUseCase,
    private val commentUseCase: CommentUseCase,
) : ViewModel() {
    enum class FiledType {
        Title,
        Text,
    }

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading
    private val _errorText = MutableLiveData("")
    val error: LiveData<String>
        get() = _errorText
    private val _title = MutableLiveData("")
    val title: LiveData<String>
        get() = _title
    private val _text = MutableLiveData("")
    val text: LiveData<String>
        get() = _text
    private val _enableSend = MutableLiveData(false)
    val enableSend: LiveData<Boolean>
        get() = _enableSend

    fun updateErrorState(message: String? = "") {
        _loading.value = false
        _errorText.value = message
    }

    fun goBack() {
        navigator.navigate {
            it.popBackStack()
        }
    }

    fun updateValue(value: String, filedType: FiledType, formType: String) {
        when (filedType) {
            FiledType.Title -> _title.value = value
            FiledType.Text -> _text.value = value
        }

        areFieldsCorrect(formType)
    }

    private fun areFieldsCorrect(formType: String) {
        val title = _title.value ?: ""
        val text = _text.value ?: ""

        _enableSend.value = (
                formType == PostCommentFormArguments.commentForm || title.trim().isNotEmpty()
                ) && text.trim().isNotEmpty()
    }

    fun sendPostComment(postId: String, commentId: String, formType: String) {
        if (_enableSend.value == true) {
            val title = _title.value ?: ""
            val text = _text.value ?: ""

            sendPostToDb(title, text, postId, commentId, formType)
        }
    }

    private fun sendPostToDb(
        title: String,
        text: String,
        postId: String,
        commentId: String,
        formType: String
    ) {
        viewModelScope.launch {
            if (formType == PostCommentFormArguments.postForm) {
                if (postId != PostCommentFormArguments.na) {
                    postUseCase.updatePost(
                        title = title,
                        text = text,
                        postId = postId,
                    )
                } else {
                    postUseCase.createPost(
                        title = title,
                        text = text
                    )
                }
            } else {
                if (commentId != PostCommentFormArguments.na) {
                    commentUseCase.updateComment(
                        text = text,
                        commentId = commentId,
                    )
                } else {
                    commentUseCase.createComment(
                        comment = text,
                        postId = postId,
                    )
                }
            }.collect {
                when (it) {
                    is PostsCommentsResponse.Error -> updateErrorState(it.exception.message)
                    PostsCommentsResponse.Idle -> _loading.value = false
                    PostsCommentsResponse.Loading -> _loading.value = true
                    is PostsCommentsResponse.Success -> goBack()
                }
            }
        }
    }

    fun getPostCommentDetails(
        formType: String,
        formAction: String,
        commentId: String,
        postId: String,
    ) {
        if (formAction == PostCommentFormArguments.edit) {
            viewModelScope.launch {
                if (formType == PostCommentFormArguments.postForm
                    && postId != PostCommentFormArguments.na) {
                    postUseCase.getPost(postId).collect {
                        when (it) {
                            is PostsCommentsResponse.Error -> updateErrorState(it.exception.message)
                            PostsCommentsResponse.Idle -> _loading.value = false
                            PostsCommentsResponse.Loading -> _loading.value = true
                            is PostsCommentsResponse.Success -> {
                                _text.value = it.data.text
                                _title.value = it.data.title
                            }
                        }
                    }
                } else if (formType == PostCommentFormArguments.commentForm
                    && commentId != PostCommentFormArguments.na
                    && postId != PostCommentFormArguments.na) {

                }
            }
        }
    }
}