import androidx.compose.ui.window.Notification
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberTrayState
import org.jetbrains.compose.resources.painterResource
import utils.Localization
import utils.OSLevelMethods
import xfullstack.composeapp.generated.resources.Res
import xfullstack.composeapp.generated.resources.logo_dark
import xfullstack.composeapp.generated.resources.logo_light

fun main() = application {
    val trayState = rememberTrayState()

    Tray(
        state = trayState,
        icon = painterResource(Res.drawable.logo_light),
        tooltip = Localization.APP_NAME,
        menu = {
            Item(
                text = Localization.EXIT, onClick = ::exitApplication
            )
        },
    )

    OSLevelMethods.showDesktopNotification = { title: String, message: String ->
        trayState.sendNotification(
            Notification(
                title = title,
                message = message,
                type = Notification.Type.Info,
            )
        )
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = Localization.APP_NAME,
        icon = painterResource(Res.drawable.logo_dark),
    ) {
        App()
    }
}