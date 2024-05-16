import androidx.compose.runtime.Composable
import app.XApp
import app.theme.XFullStackTheme

@Composable
fun App() {
    XFullStackTheme(
        darkTheme = true,
    ) {
        XApp()
    }
}