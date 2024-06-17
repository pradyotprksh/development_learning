import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.jetbrains.compose.resources.painterResource
import utils.Localization
import xfullstack.composeapp.generated.resources.Res
import xfullstack.composeapp.generated.resources.logo_light

fun main() = application {
    Tray(
        icon = painterResource(Res.drawable.logo_light),
        tooltip = Localization.APP_NAME,
        menu = {
            Item(
                text = Localization.EXIT,
                onClick = ::exitApplication
            )
        }
    )

    Window(
        onCloseRequest = ::exitApplication,
        title = "XFullStack",
    ) {
        App()
    }
}