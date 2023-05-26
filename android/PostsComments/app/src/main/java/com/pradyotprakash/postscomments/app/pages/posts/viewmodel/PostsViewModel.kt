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
import com.pradyotprakash.postscomments.core.utils.PostArguments
import com.pradyotprakash.postscomments.domain.models.PostDetails
import com.pradyotprakash.postscomments.domain.usecases.AuthenticationUseCase
import com.pradyotprakash.postscomments.domain.usecases.PostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val authenticationUseCase: AuthenticationUseCase,
    private val authStateListener: AuthStateListener,
    private val navigator: Navigator,
    private val postUseCase: PostUseCase,
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
    private val _posts = MutableLiveData<List<PostDetails>>(emptyList())
    val posts: LiveData<List<PostDetails>>
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

    private fun logoutUser() {
        authenticationUseCase.logoutUser()
        authStateListener.stateChange(AuthState.Unauthenticated)
    }

    fun openCreatePostScreen() {
        navigator.navigate {
            it.navigate(
                "${Routes.Post.route}${PostArguments.createPost}/${PostArguments.defaultPostId}"
            )
        }
    }

    fun getPosts() {
        viewModelScope.launch {
            postUseCase.getPosts().collect {
                when (it) {
                    is PostsCommentsResponse.Error -> updateErrorState(it.exception.message)
                    PostsCommentsResponse.Idle -> _loading.value = false
                    PostsCommentsResponse.Loading -> _loading.value = true
                    is PostsCommentsResponse.Success -> {
                        _posts.value = emptyList()
                        _posts.value = it.data ?: emptyList()
                    }
                }
            }
        }
    }

    fun isFromCurrentUser(createdBy: String): Boolean =
        authenticationUseCase.getCurrentUserId() == createdBy
}