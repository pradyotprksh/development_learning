package app.pages.home.bottomBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import app.navigation.Routes
import app.navigation.path

sealed class HomeBottomNavItems(
    val route: String, val icon: ImageVector
) {
    data object Home : HomeBottomNavItems(Routes.Home.path(), Icons.Default.Home)
    data object Search : HomeBottomNavItems(Routes.HomeSearch.path(), Icons.Default.Search)
    data object Grok : HomeBottomNavItems(Routes.HomeGrok.path(), Icons.Default.CheckCircle)
    data object Communities :
        HomeBottomNavItems(Routes.HomeCommunities.path(), Icons.Default.Person)

    data object Notification : HomeBottomNavItems(
        Routes.HomeNotifications.path(),
        Icons.Default.Notifications,
    )

    data object Messages : HomeBottomNavItems(Routes.HomeMessages.path(), Icons.Default.Email)
}