package utils

import androidx.compose.ui.unit.DpSize

object OSLevelMethods {
    var showDesktopNotification: ((String, String) -> Unit)? = null
    var createTweet: (() -> Unit)? = null
    var makeWindowFullScreen: (() -> Unit)? = null
    var windowSize: DpSize? = null
}