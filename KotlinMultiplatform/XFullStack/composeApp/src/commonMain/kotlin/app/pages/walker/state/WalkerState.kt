package app.pages.walker.state

import androidx.compose.ui.geometry.Offset

data class WalkerState(
    val animateHuman: Boolean = false,
    val humanRotation: Float = 0f,
    val humanPosition: Offset = Offset(0f, 0f),
)
