package com.pradyotprakash.postscomments.app.pages.post.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradyotprakash.postscomments.core.navigator.Navigator
import com.pradyotprakash.postscomments.core.response.PostsCommentsResponse
import com.pradyotprakash.postscomments.domain.usecases.PostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val navigator: Navigator,
    private val postUseCase: PostUseCase,
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

    fun goToPostsScreen() {
        navigator.navigate {
            it.popBackStack()
        }
    }

    fun updateValue(value: String, filedType: FiledType) {
        when (filedType) {
            FiledType.Title -> _title.value = value
            FiledType.Text -> _text.value = value
        }

        areFieldsCorrect()
    }

    private fun areFieldsCorrect() {
        val title = _title.value ?: ""
        val text = _text.value ?: ""

        _enableSend.value = title.isNotEmpty() && text.isNotEmpty()
    }

    fun sendPost() {
        if (_enableSend.value == true) {
            val title = _title.value ?: ""
            val text = _text.value ?: ""

            sendPostToDb(title, text)
        }
    }

    private fun sendPostToDb(title: String, text: String) {
        viewModelScope.launch {
            postUseCase.createPost(
                title = title,
                text = text
            ).collect {
                when (it) {
                    is PostsCommentsResponse.Error -> updateErrorState(it.exception.message)
                    PostsCommentsResponse.Idle -> _loading.value = false
                    PostsCommentsResponse.Loading -> _loading.value = true
                    is PostsCommentsResponse.Success -> goToPostsScreen()
                }
            }
        }
    }
}