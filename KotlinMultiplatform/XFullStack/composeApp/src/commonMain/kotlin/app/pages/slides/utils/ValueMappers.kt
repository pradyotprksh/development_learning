package app.pages.slides.utils

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import utils.asEnumOrDefault

@Composable
fun String.backgroundColor() = when (this.asEnumOrDefault(Colors.Background)) {
    Colors.Primary -> {
        MaterialTheme.colorScheme.primary
    }

    else -> {
        MaterialTheme.colorScheme.background
    }
}

@Composable
fun String.textColor() = when (this.asEnumOrDefault(Colors.Background)) {
    Colors.Primary -> {
        MaterialTheme.colorScheme.primary
    }

    Colors.OnPrimary -> {
        MaterialTheme.colorScheme.onPrimary
    }

    else -> {
        LocalContentColor.current
    }
}

@Composable
fun String.textStyle() = when (this.asEnumOrDefault(Style.BodyMedium)) {
    Style.HeadlineLarge -> MaterialTheme.typography.headlineLarge
    else -> MaterialTheme.typography.bodyMedium
}

@Composable
fun String.textAlignment() = when (this.asEnumOrDefault(TextAlignment.Start)) {
    TextAlignment.Center -> TextAlign.Center
    else -> TextAlign.Start
}

