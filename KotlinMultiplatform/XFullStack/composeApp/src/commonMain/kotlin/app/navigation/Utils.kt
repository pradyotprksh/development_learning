package app.navigation

fun showBottomNavBar(route: String) = listOf(
    Routes.Home.path(),
    Routes.HomeSearch.path(),
    Routes.HomeGrok.path(),
    Routes.HomeCommunities.path(),
    Routes.HomeNotifications.path(),
    Routes.HomeMessages.path(),
).contains(route)

fun showDrawer(route: String) = showBottomNavBar(route)