package com.pradyotprakash.postscomments.app.pages.posts.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradyotprakash.postscomments.app.composables.ConfirmationDialog
import com.pradyotprakash.postscomments.app.localization.TR
import com.pradyotprakash.postscomments.core.auth.AuthState
import com.pradyotprakash.postscomments.core.auth.AuthStateListener
import com.pradyotprakash.postscomments.core.navigator.Navigator
import com.pradyotprakash.postscomments.core.navigator.Routes
import com.pradyotprakash.postscomments.core.response.PostsCommentsResponse
import com.pradyotprakash.postscomments.core.utils.PostCommentFormArguments
import com.pradyotprakash.postscomments.domain.models.PostCompleteDetails
import com.pradyotprakash.postscomments.domain.usecases.AuthenticationUseCase
import com.pradyotprakash.postscomments.domain.usecases.CommentUseCase
import com.pradyotprakash.postscomments.domain.usecases.PostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val authenticationUseCase: AuthenticationUseCase,
    private val authStateListener: AuthStateListener,
    private val navigator: Navigator,
    private val postUseCase: PostUseCase,
    private val commentUseCase: CommentUseCase,
) : ViewModel() {
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading
    private val _errorText = MutableLiveData("")
    val error: LiveData<String>
        get() = _errorText
    private val _confirmationDialog = MutableLiveData(ConfirmationDialog())
    val confirmationDialog: LiveData<ConfirmationDialog>
        get() = _confirmationDialog
    private val _posts = MutableLiveData<List<PostCompleteDetails>>(emptyList())
    val posts: LiveData<List<PostCompleteDetails>>
        get() = _posts

    fun updateErrorState(message: String? = "") {
        _loading.value = false
        _errorText.value = message
    }

    fun confirmLogOutUser() {
        _confirmationDialog.value = ConfirmationDialog(
            text = TR.confirmLogout,
            onConfirm = {
                _confirmationDialog.value = ConfirmationDialog()
                logoutUser()
            },
            onDismiss = {
                _confirmationDialog.value = ConfirmationDialog()
            },
        )
    }

    fun confirmDeletePosts(postId: String) {
        _confirmationDialog.value = ConfirmationDialog(
            text = TR.surePostDelete,
            onConfirm = {
                _confirmationDialog.value = ConfirmationDialog()
                deletePost(postId)
            },
            onDismiss = {
                _confirmationDialog.value = ConfirmationDialog()
            },
        )
    }

    private fun deletePost(postId: String) {
        viewModelScope.launch {
            postUseCase.deletePost(postId = postId).collect {
                when (it) {
                    is PostsCommentsResponse.Error -> updateErrorState(it.exception.message)
                    is PostsCommentsResponse.Success -> deleteComments(postId)
                    else -> {}
                }
            }
        }
    }

    private suspend fun deleteComments(postId: String) {
        commentUseCase.getComments(postId = postId).collect {
            when (it) {
                is PostsCommentsResponse.Success -> {
                    it.data.forEach { comment ->
                        commentUseCase.deleteComment(commentId = comment.commentId)
                    }
                }
                else -> {}
            }
        }
    }

    private fun logoutUser() {
        authenticationUseCase.logoutUser()
        authStateListener.stateChange(AuthState.Unauthenticated)
    }

    fun openCreatePostScreen() {
        navigator.navigate {
            it.navigate(
                Routes.PostForm.route + PostCommentFormArguments.postForm + "/${PostCommentFormArguments.create}" + "/${PostCommentFormArguments.na}" + "/${PostCommentFormArguments.na}"
            )
        }
    }

    fun getPosts() {
        viewModelScope.launch {
            postUseCase.getPosts().collect {
                when (it) {
                    is PostsCommentsResponse.Error -> updateErrorState(it.exception.message)
                    is PostsCommentsResponse.Success -> {
                        _posts.value = emptyList()
                        _posts.value = it.data ?: emptyList()
                    }

                    else -> {}
                }
            }
        }
    }

    fun isFromCurrentUser(createdBy: String): Boolean =
        authenticationUseCase.getCurrentUserId() == createdBy

    fun editPost(postId: String) {
        navigator.navigate {
            it.navigate(
                Routes.PostForm.route + PostCommentFormArguments.postForm + "/${PostCommentFormArguments.edit}" + "/$postId" + "/${PostCommentFormArguments.na}"
            )
        }
    }

    fun goToPostDetails(postId: String) {
        navigator.navigate {
            it.navigate(
                "${Routes.Post.route}$postId"
            )
        }
    }
}