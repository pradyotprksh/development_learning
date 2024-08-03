package app.pages.walker.viewModel

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.ViewModel
import app.pages.walker.state.WalkerState
import app.pages.walker.utils.Directions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import utils.Logger
import utils.LoggerLevel
import utils.OSLevelMethods
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.random.Random

class WalkerViewModel : ViewModel() {
    private val _walkerState = MutableStateFlow(WalkerState())
    val walkerState = _walkerState.asStateFlow()

    private var mousePosition: Offset = Offset.Zero

    fun pointerInTheBound(isInBound: Boolean) {
        val humanPosition = getNewHumanPosition(isInBound)
        _walkerState.update {
            it.copy(
                animateHuman = if (isInBound) true else it.animateHuman,
                humanRotation = if (isInBound) 5f else it.humanRotation,
                humanPosition = humanPosition,
            )
        }
    }

    private fun getNewHumanPosition(isInBound: Boolean): Offset {
        val currentHumanPosition = _walkerState.value.humanPosition

        val newOffset = if (!isInBound) {
            currentHumanPosition
        } else {
            getRandomBasedOffset()
        }

        OSLevelMethods.windowSize?.let { windowSize ->
            val isInTheScreen = isOffsetInWindowSize(windowSize, newOffset)
            Logger.log(
                LoggerLevel.Info,
                "windowSize $windowSize newOffset $newOffset isInTheScreen $isInTheScreen"
            )
        }

        return newOffset
    }

    private fun getRandomBasedOffset(): Offset {
        val mousePositionX = mousePosition.x.toDouble()
        val mousePositionY = mousePosition.y.toDouble()

        val offset = if (Random.nextBoolean()) {
            Offset(
                x = (if (Random.nextBoolean()) mousePositionX.plus(
                    Random.nextDouble(
                        mousePositionX, mousePositionX + 5f
                    )
                ) else mousePositionX.minus(
                    Random.nextDouble(
                        mousePositionX, mousePositionX + 5f
                    )
                )).toFloat(),
                y = (if (Random.nextBoolean()) mousePositionY.plus(
                    Random.nextDouble(
                        mousePositionY, mousePositionY + 5f
                    )
                ) else mousePositionY.minus(
                    Random.nextDouble(
                        mousePositionY, mousePositionY + 5f
                    )
                )).toFloat(),
            )
        } else {
            Offset(
                x = (if (Random.nextBoolean()) mousePositionY.plus(
                    Random.nextDouble(
                        mousePositionY, mousePositionY + 5f
                    )
                ) else mousePositionY.minus(
                    Random.nextDouble(
                        mousePositionY, mousePositionY + 5f
                    )
                )).toFloat(),
                y = (if (Random.nextBoolean()) mousePositionX.plus(
                    Random.nextDouble(
                        mousePositionX, mousePositionX + 5f
                    )
                ) else mousePositionX.minus(
                    Random.nextDouble(
                        mousePositionX, mousePositionX + 5f
                    )
                )).toFloat(),
            )
        }

        return offset
    }

    private fun getDirectionBasedOffset(currentHumanPosition: Offset): Offset {
        val direction = getDirection(currentHumanPosition, mousePosition)
        return when (direction) {
            Directions.Below -> {
                Offset(
                    x = mousePosition.x,
                    y = mousePosition.y.minus(5f),
                )
            }

            Directions.Above -> {
                Offset(
                    x = mousePosition.x,
                    y = mousePosition.y.plus(5f),
                )
            }

            Directions.Right -> {
                Offset(
                    x = mousePosition.x.minus(5f),
                    y = mousePosition.y,
                )
            }

            Directions.Left -> {
                Offset(
                    x = mousePosition.x.plus(5f),
                    y = mousePosition.y,
                )
            }

            Directions.BottomRight -> {
                Offset(
                    x = mousePosition.x.minus(5f),
                    y = mousePosition.y.minus(5f),
                )
            }

            Directions.TopRight -> {
                Offset(
                    x = mousePosition.x.minus(5f),
                    y = mousePosition.y.plus(5f),
                )
            }

            Directions.BottomLeft -> {
                Offset(
                    x = mousePosition.x.plus(5f),
                    y = mousePosition.y.minus(5f),
                )
            }

            Directions.TopLeft -> {
                Offset(
                    x = mousePosition.x.plus(5f),
                    y = mousePosition.y.plus(5f),
                )
            }

            Directions.SamePosition, Directions.Unknown -> {
                currentHumanPosition
            }
        }
    }

    private fun getDirection(from: Offset, to: Offset): Directions {
        val deltaX = from.x - to.x
        val deltaY = from.y - to.y
        val angle = atan2(deltaY, deltaX) * (180 / PI)

        return when (angle) {
            in -22.5..22.5 -> Directions.Right
            in 22.5..67.5 -> Directions.TopRight
            in 67.5..112.5 -> Directions.Above
            in 112.5..157.5 -> Directions.TopLeft
            in -67.5..-22.5 -> Directions.BottomRight
            in -112.5..-67.5 -> Directions.Below
            in -157.5..-112.5 -> Directions.BottomLeft
            else -> Directions.Left
        }
    }

    fun humanRotationAnimationFinished() {
        if (_walkerState.value.animateHuman) {
            _walkerState.update {
                it.copy(
                    humanRotation = -1 * it.humanRotation,
                )
            }
        }
    }

    fun mousePointerPosition(position: Offset) {
        mousePosition = position
    }

    fun humanPositionAnimationFinished() {
        _walkerState.update {
            it.copy(
                animateHuman = true,
                humanRotation = 0f,
            )
        }
    }

    private fun isOffsetInWindowSize(windowSize: DpSize, offset: Offset): Boolean {
        val windowSizeInPx =
            IntSize(windowSize.width.value.toInt(), windowSize.height.value.toInt())
        return offset.x >= 0 && offset.y >= 0 && offset.x <= windowSizeInPx.width && offset.y <= windowSizeInPx.height
    }
}