package app.pages.home.search.viewModel

import androidx.lifecycle.ViewModel
import app.pages.home.search.state.HomeSearchState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeSearchViewModel : ViewModel() {
    private val _homeSearchState = MutableStateFlow(HomeSearchState())
    val homeSearchState = _homeSearchState.asStateFlow()

    fun removeSnackBarMessage() {
        _homeSearchState.value = _homeSearchState.value.copy(
            snackBarMessage = null
        )
    }
}