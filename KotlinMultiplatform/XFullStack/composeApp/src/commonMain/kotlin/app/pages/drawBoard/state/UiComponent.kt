package app.pages.drawBoard.state

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import utils.Localization

sealed class UiComponent(
    val name: String,
    val height: Dp,
    val width: Dp,
    val borderWidth: Dp,
    val shape: Shape?,
) {
    data object BoxComponent : UiComponent(
        name = Localization.BOX,
        height = 70.dp,
        width = 140.dp,
        borderWidth = Dp.Hairline,
        shape = RoundedCornerShape(size = 5.dp),
    )

    data object OvalComponent : UiComponent(
        name = Localization.OVAL,
        height = 70.dp,
        width = 70.dp,
        borderWidth = Dp.Hairline,
        shape = CircleShape,
    )

    data object LineComponent : UiComponent(
        name = Localization.LINE,
        height = Dp.Hairline,
        width = 140.dp,
        borderWidth = Dp.Hairline,
        shape = null,
    )
}