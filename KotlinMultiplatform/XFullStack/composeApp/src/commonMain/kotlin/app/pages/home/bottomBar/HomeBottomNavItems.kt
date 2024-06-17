package app.pages.home.bottomBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import app.navigation.Routes
import app.navigation.path

sealed class HomeBottomNavItems(
    val route: String, val icon: ImageVector,
) {
    data object Home : HomeBottomNavItems(Routes.Home.path(), Icons.Default.Home)
    data object Search : HomeBottomNavItems(Routes.HomeSearch.path(), Icons.Default.Search)
    data object Grok : HomeBottomNavItems(Routes.HomeGrok.path(), Icons.Default.Circle)
    data object Communities :
        HomeBottomNavItems(Routes.HomeCommunities.path(), Icons.Default.People)

    data object Notification : HomeBottomNavItems(
        Routes.HomeNotifications.path(),
        Icons.Default.Notifications,
    )

    data object Messages :
        HomeBottomNavItems(Routes.HomeMessages.path(), Icons.AutoMirrored.Filled.Message)
}