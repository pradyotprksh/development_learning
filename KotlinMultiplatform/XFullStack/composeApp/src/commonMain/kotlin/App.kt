import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import app.XApp
import app.theme.XFullStackTheme

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun App() {
    val windowSizeClass = calculateWindowSizeClass()
    val isPhone =
        windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact || windowSizeClass.widthSizeClass == WindowWidthSizeClass.Medium

    XFullStackTheme(
        darkTheme = true,
    ) {
        XApp(
            isPhone = isPhone,
        )
    }
}