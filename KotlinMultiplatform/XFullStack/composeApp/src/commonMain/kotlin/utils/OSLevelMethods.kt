package utils

object OSLevelMethods {
    var showDesktopNotification: ((String, String) -> Unit)? = null
    var createTweet: (() -> Unit)? = null
    var makeWindowFullScreen: (() -> Unit)? = null
}