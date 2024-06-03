package app.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = TwitterBlue,
    onPrimary = Color.White,
    primaryContainer = TwitterSurface,
    onPrimaryContainer = TwitterLightGray,
    inversePrimary = Color(0xFF4D8CB9),

    secondary = TwitterDarkGreen,
    onSecondary = Color.White,
    secondaryContainer = TwitterDarkGray,
    onSecondaryContainer = TwitterLightGray,

    tertiary = TwitterTeal,
    onTertiary = Color.White,
    tertiaryContainer = TwitterSurface,
    onTertiaryContainer = TwitterLightGray,

    background = TwitterDarkBackground,
    onBackground = Color.White,

    surface = TwitterSurface,
    onSurface = Color.White,
    surfaceVariant = TwitterGreenGray,
    onSurfaceVariant = TwitterLightGray,
    surfaceTint = TwitterBlue,

    inverseSurface = TwitterLightGreenGray,
    inverseOnSurface = TwitterDarkGreenGray,

    error = TwitterRed,
    onError = Color.White,
    errorContainer = Color(0xFF8E0015),
    onErrorContainer = Color(0xFFFFDAD6),

    outline = TwitterOutline,
    outlineVariant = TwitterDarkGray,

    scrim = Color.Black,

    surfaceBright = TwitterSurface.copy(alpha = 0.95f),
    surfaceDim = TwitterSurface.copy(alpha = 0.8f),
    surfaceContainer = TwitterSurface.copy(alpha = 0.9f),
    surfaceContainerHigh = TwitterSurface.copy(alpha = 0.85f),
    surfaceContainerHighest = TwitterSurface.copy(alpha = 0.75f),
    surfaceContainerLow = TwitterSurface.copy(alpha = 0.95f),
    surfaceContainerLowest = TwitterSurface.copy(alpha = 0.98f),
)

private val LightColorScheme = lightColorScheme(
    primary = TwitterBlue,
    onPrimary = Color.White,
    primaryContainer = TwitterLightBackground,
    onPrimaryContainer = TwitterDarkGray,
    inversePrimary = Color(0xFF4D8CB9),

    secondary = TwitterDarkGreen,
    onSecondary = Color.White,
    secondaryContainer = TwitterLightGray,
    onSecondaryContainer = TwitterDarkGray,

    tertiary = TwitterTeal,
    onTertiary = Color.White,
    tertiaryContainer = TwitterLightBackground,
    onTertiaryContainer = TwitterDarkGray,

    background = TwitterLightBackground,
    onBackground = Color.Black,

    surface = TwitterSurface,
    onSurface = Color.Black,
    surfaceVariant = TwitterGreenGray,
    onSurfaceVariant = Color.Black,
    surfaceTint = TwitterBlue,

    inverseSurface = TwitterDarkGray,
    inverseOnSurface = TwitterLightGreenGray,

    error = TwitterRed,
    onError = Color.White,
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF8E0015),

    outline = TwitterOutline,
    outlineVariant = TwitterDarkGray,

    scrim = Color.Black,

    surfaceBright = TwitterLightBackground.copy(alpha = 0.95f),
    surfaceDim = TwitterLightBackground.copy(alpha = 0.8f),
    surfaceContainer = TwitterLightBackground.copy(alpha = 0.9f),
    surfaceContainerHigh = TwitterLightBackground.copy(alpha = 0.85f),
    surfaceContainerHighest = TwitterLightBackground.copy(alpha = 0.75f),
    surfaceContainerLow = TwitterLightBackground.copy(alpha = 0.95f),
    surfaceContainerLowest = TwitterLightBackground.copy(alpha = 0.98f),
)

val LightAndroidGradientColors = GradientColors(container = DarkGreenGray95)
val DarkAndroidGradientColors = GradientColors(container = Color.Black)
val LightAndroidBackgroundTheme = BackgroundTheme(color = DarkGreenGray95)
val DarkAndroidBackgroundTheme = BackgroundTheme(color = Color.Black)

@Composable
fun XFullStackTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val gradientColors = when {
        darkTheme -> DarkAndroidGradientColors
        else -> LightAndroidGradientColors
    }

    val backgroundTheme = if (darkTheme) DarkAndroidBackgroundTheme else LightAndroidBackgroundTheme

    val tintTheme = TintTheme(colorScheme.primary)

    CompositionLocalProvider(
        LocalGradientColors provides gradientColors,
        LocalBackgroundTheme provides backgroundTheme,
        LocalTintTheme provides tintTheme,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content,
        )
    }
}
