package com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.screen.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.state.PlayerState

@Composable
fun Player(
    modifier: Modifier = Modifier,
    player: PlayerState,
    iconSize: Dp = 24.dp,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Icon(
            Icons.Filled.Person,
            contentDescription = Icons.Filled.Person.name,
            tint = if (player.isThePlayer) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
            modifier = Modifier.size(iconSize)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            player.name,
            style = MaterialTheme.typography.labelSmall,
        )
    }
}