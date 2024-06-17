package app.pages.home.search.state

import utils.Localization

data class HomeSearchState(
    val showLoading: Boolean = false,
    val snackBarMessage: String? = null,
    val tabsDetails: List<String> = listOf(
        Localization.FOR_YOU,
        Localization.TRENDING,
        Localization.NEWS,
        Localization.SPORTS,
        Localization.ENTERTAINMENT,
    ),
)
