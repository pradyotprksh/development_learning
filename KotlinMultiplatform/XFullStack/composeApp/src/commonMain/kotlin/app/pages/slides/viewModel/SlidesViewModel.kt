package app.pages.slides.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.pages.slides.state.SlidesState
import di.SharedModulesDi
import domain.repositories.slides.SlidesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import utils.Localization
import utils.extensions.debounce
import xfullstack.composeapp.generated.resources.Res

class SlidesViewModel(
    private val slidesRepository: SlidesRepository = SharedModulesDi.Instance.slidesRepository,
) : ViewModel() {
    private val _slidesState = MutableStateFlow(SlidesState())
    val slidesState = _slidesState.asStateFlow()

    private fun updateLoaderState(showLoader: Boolean) {
        _slidesState.value = _slidesState.value.copy(
            showLoading = showLoader
        )
    }

    private fun showMessage(message: String) {
        _slidesState.value = _slidesState.value.copy(
            snackBarMessage = message,
        )
    }

    fun removeSnackBarMessage() {
        _slidesState.value = _slidesState.value.copy(
            snackBarMessage = null
        )
    }

    @OptIn(ExperimentalResourceApi::class)
    fun loadSlidesDetails(slidesFilePath: String) {
        viewModelScope.launch(Dispatchers.IO) {
            updateLoaderState(true)
            try {
                val fileContent = Res.readBytes(slidesFilePath).decodeToString()
                val slidesDetails = slidesRepository.parseStringToSlides(fileContent)
                _slidesState.update {
                    it.copy(
                        slidesDetails = slidesDetails,
                    )
                }
            } catch (e: Exception) {
                showMessage(e.message ?: Localization.DEFAULT_ERROR_MESSAGE)
            }
            updateLoaderState(false)
        }
    }

    fun updateKeyEventDetails(name: String?, symbol: String?) {
        if (name != null || symbol != null) {
            _slidesState.update {
                it.copy(
                    keyEvent = Pair(
                        symbol,
                        name,
                    ),
                    showKeyEvent = true,
                )
            }
            debounce<Unit>(
                waitMs = 1000,
                scope = viewModelScope,
            ) {
                _slidesState.update {
                    it.copy(
                        showKeyEvent = false,
                    )
                }
            }.invoke(Unit)
        }
    }
}