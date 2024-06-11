package app.navigation

import utils.Constants.ConstValues.PARENT_TWEET_ID
import utils.Constants.ConstValues.TWEET_ID
import utils.Constants.ConstValues.USERNAME_EMAIL_PHONE

fun Routes.path(): String {
    if (arguments.isEmpty()) return route
    var completePath = route
    for (argument in arguments) {
        completePath += "{${argument}}/"
    }
    return completePath.removeSuffix("/")
}

/**
 * Routes
 */
enum class Routes(
    val route: String,
    val arguments: List<String> = emptyList(),
) {
    Splash("splash/"),
    AuthenticationOption("authentication-option/"),
    Login("login/", listOf(USERNAME_EMAIL_PHONE)),
    Register("register/"),
    Home("home/"),
    HomeSearch("home/search/"),
    HomeGrok("home/grok/"),
    HomeCommunities("home/communities/"),
    HomeNotifications("home/notifications/"),
    HomeMessages("home/messages/"),
    CreateTweet("create-tweet/", listOf(PARENT_TWEET_ID)),
    TweetDetails("tweet-details/", listOf(TWEET_ID)),
}
