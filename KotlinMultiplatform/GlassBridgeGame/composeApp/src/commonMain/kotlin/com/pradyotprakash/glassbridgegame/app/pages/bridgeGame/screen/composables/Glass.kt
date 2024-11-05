package com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.screen.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.state.GlassState
import com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.state.PlayerState

@Composable
fun Glass(
    modifier: Modifier = Modifier,
    glass: GlassState,
    player: PlayerState?,
    onBridgeGlassTap: () -> Unit,
) {
    val glassBreakEffect by animateFloatAsState(
        targetValue = if (glass.isBroken) 0.5f else 1f,
        animationSpec = tween(durationMillis = 300, easing = LinearEasing)
    )
    Box(
        modifier = modifier,
    ) {
        AnimatedVisibility(
            visible = player != null,
            enter = scaleIn(initialScale = 0.5f) + slideInVertically(initialOffsetY = { -40 }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { 40 }) + fadeOut() + scaleOut(targetScale = 0.5f),
            modifier = Modifier.align(Alignment.Center),
        ) {
            player?.let {
                Player(
                    player = it,
                    iconSize = 48.dp,
                )
            }
        }
        Box(
            modifier = Modifier.clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f * glassBreakEffect))
                .blur(10.dp).border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f * glassBreakEffect),
                    shape = RoundedCornerShape(10.dp)
                ).graphicsLayer(
                    scaleX = glassBreakEffect,
                    scaleY = glassBreakEffect,
                ).padding(16.dp).height(100.dp).fillMaxWidth().clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                ) {
                    onBridgeGlassTap()
                },
        ) {}
    }
}