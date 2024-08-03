package app.pages.walker.screen

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.pages.walker.viewModel.WalkerViewModel
import utils.Localization

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WalkerScreen(
    walkerViewModel: WalkerViewModel = viewModel { WalkerViewModel() },
    navigateBack: () -> Unit,
) {
    val walkerState by walkerViewModel.walkerState.collectAsState()

    val humanRotationValue by animateFloatAsState(
        targetValue = walkerState.humanRotation,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessHigh
        ),
        finishedListener = {
            walkerViewModel.humanRotationAnimationFinished()
        }
    )

    val humanOffsetValue by animateIntOffsetAsState(
        targetValue = IntOffset(
            walkerState.humanPosition.x.toInt(),
            walkerState.humanPosition.y.toInt()
        ),
        animationSpec = tween(
            durationMillis = 1000,
            easing = LinearEasing,
        ),
        finishedListener = {
            walkerViewModel.humanPositionAnimationFinished()
        }
    )

    val primaryColor = MaterialTheme.colorScheme.primary

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        Localization.WALKER,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = navigateBack,
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = Icons.AutoMirrored.Filled.ArrowBack.name,
                        )
                    }
                },
            )
        },
        modifier = Modifier.pointerInput(Unit) {
            awaitPointerEventScope {
                while (true) {
                    val event = awaitPointerEvent()
                    val position = event.changes.first().position
                    walkerViewModel.mousePointerPosition(position)
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .offset {
                        humanOffsetValue
                    }
                    .pointerInput(Unit) {
                        awaitPointerEventScope {
                            while (true) {
                                val event = awaitPointerEvent()
                                if (event.type == PointerEventType.Enter) {
                                    walkerViewModel.pointerInTheBound(true)
                                } else if (event.type == PointerEventType.Exit) {
                                    walkerViewModel.pointerInTheBound(false)
                                }
                            }
                        }
                    }
            ) {
                Spacer(
                    modifier = Modifier
                        .drawWithCache {
                            val path = Path()
                            // Body
                            path.moveTo(size.width / 2f + 15f, 30f)
                            path.lineTo(size.width / 2f + 15f, 75f)
                            // Left leg
                            path.moveTo(size.width / 2f + 15f, 75f)
                            path.lineTo(size.width / 2f, 95f)
                            // Right leg
                            path.moveTo(size.width / 2f + 15f, 75f)
                            path.lineTo(size.width / 2f + 30f, 95f)
                            // Left hand
                            path.moveTo(size.width / 2f + 15f, 45f)
                            path.lineTo(size.width / 2f, 50f)
                            // Right hand
                            path.moveTo(size.width / 2f + 15f, 45f)
                            path.lineTo(size.width / 2f + 30f, 50f)
                            path.close()
                            onDrawBehind {
                                rotate(
                                    humanRotationValue,
                                ) {
                                    // Head
                                    drawOval(
                                        color = primaryColor,
                                        size = Size(
                                            width = 30f,
                                            height = 30f,
                                        ),
                                        style = Stroke(
                                            width = Dp.Hairline.value,
                                        ),
                                        topLeft = Offset(
                                            x = size.width / 2f,
                                            y = 0f,
                                        )
                                    )
                                    // Body
                                    drawPath(
                                        path,
                                        primaryColor,
                                        style = Stroke(width = Dp.Hairline.value),
                                    )
                                }
                            }
                        }
                        .align(Alignment.Center)
                )
            }
        }
    }
}