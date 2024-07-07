package app.pages.home.grok.viewModel

import androidx.lifecycle.ViewModel
import app.pages.home.grok.state.HomeGrokState
import di.SharedModulesDi
import domain.repositories.grok.GrokRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeGrokViewModel(
    private val grokRepository: GrokRepository = SharedModulesDi.Instance.grokRepository,
) : ViewModel() {
    private val _homeGrokState = MutableStateFlow(HomeGrokState())
    val homeGrokState = _homeGrokState.asStateFlow()

    fun removeSnackBarMessage() {
        _homeGrokState.value = _homeGrokState.value.copy(
            snackBarMessage = null
        )
    }
}