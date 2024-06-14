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

fun showAppBar(route: String) = showDrawer(route)

fun showFloatingActionButton(route: String) = listOf(
    Routes.Home.path(),
    Routes.HomeSearch.path(),
    Routes.HomeCommunities.path(),
    Routes.HomeNotifications.path(),
    Routes.HomeMessages.path(),
    Routes.ProfileDetails.path(),
).contains(route)