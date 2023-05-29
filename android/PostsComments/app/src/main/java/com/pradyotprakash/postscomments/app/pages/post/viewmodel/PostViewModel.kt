package com.pradyotprakash.postscomments.app.pages.post.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradyotprakash.postscomments.app.composables.ConfirmationDialog
import com.pradyotprakash.postscomments.app.localization.TR
import com.pradyotprakash.postscomments.core.navigator.Navigator
import com.pradyotprakash.postscomments.core.navigator.Routes
import com.pradyotprakash.postscomments.core.response.PostsCommentsResponse
import com.pradyotprakash.postscomments.core.toast.ToastListener
import com.pradyotprakash.postscomments.core.utils.PostCommentFormArguments
import com.pradyotprakash.postscomments.domain.models.CommentCompleteDetails
import com.pradyotprakash.postscomments.domain.usecases.AuthenticationUseCase
import com.pradyotprakash.postscomments.domain.usecases.CommentUseCase
import com.pradyotprakash.postscomments.domain.usecases.PostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val navigator: Navigator,
    private val postUseCase: PostUseCase,
    private val commentUseCase: CommentUseCase,
    private val authenticationUseCase: AuthenticationUseCase,
    private val toastListener: ToastListener,
) : ViewModel() {
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
    private val _comments = MutableLiveData<List<CommentCompleteDetails>>(emptyList())
    val comments: LiveData<List<CommentCompleteDetails>>
        get() = _comments
    private val _confirmationDialog = MutableLiveData(ConfirmationDialog())
    val confirmationDialog: LiveData<ConfirmationDialog>
        get() = _confirmationDialog

    fun updateErrorState(message: String? = "") {
        _loading.value = false
        _errorText.value = message
    }

    fun getPostDetails(postId: String) {
        viewModelScope.launch {
            postUseCase.getPost(postId).collect {
                when (it) {
                    is PostsCommentsResponse.Error -> updateErrorState(it.exception.message)
                    PostsCommentsResponse.Idle -> _loading.value = false
                    PostsCommentsResponse.Loading -> _loading.value = true
                    is PostsCommentsResponse.Success -> {
                        _text.value = it.data.text
                        _title.value = it.data.title
                        getComments(postId)
                    }
                }
            }
        }
    }

    private suspend fun getComments(postId: String) {
        commentUseCase.getComments(postId).collect {
            when (it) {
                is PostsCommentsResponse.Error -> updateErrorState(it.exception.message)
                is PostsCommentsResponse.Success -> {
                    _loading.value = false
                    _comments.value = emptyList()
                    _comments.value = it.data ?: emptyList()
                }

                else -> {}
            }
        }
    }

    fun goBack() {
        navigator.navigate {
            it.popBackStack()
        }
    }

    fun createComment(postId: String) {
        navigator.navigate {
            it.navigate(
                Routes.PostForm.route +
                        PostCommentFormArguments.commentForm +
                        "/${PostCommentFormArguments.create}" +
                        "/$postId" +
                        "/${PostCommentFormArguments.na}"
            )
        }
    }

    fun isCommentByCurrentUser(createdBy: String): Boolean =
        authenticationUseCase.getCurrentUserId() == createdBy

    private fun deleteComment(commentId: String) {
        viewModelScope.launch {
            commentUseCase.deleteComment(commentId = commentId).collect {
                when (it) {
                    is PostsCommentsResponse.Error -> updateErrorState(it.exception.message)
                    is PostsCommentsResponse.Success -> {
                        toastListener.showToast(TR.commentDeleted)
                    }
                    else -> {}
                }
            }
        }
    }

    fun editComment(commentId: String) {
        navigator.navigate {
            it.navigate(
                Routes.PostForm.route +
                        PostCommentFormArguments.commentForm +
                        "/${PostCommentFormArguments.edit}" +
                        "/${PostCommentFormArguments.na}" +
                        "/$commentId"
            )
        }
    }

    fun confirmDeleteComment(commentId: String) {
        _confirmationDialog.value = ConfirmationDialog(
            text = TR.sureCommentDelete,
            onConfirm = {
                _confirmationDialog.value = ConfirmationDialog()
                deleteComment(commentId)
            },
            onDismiss = {
                _confirmationDialog.value = ConfirmationDialog()
            },
        )
    }
}