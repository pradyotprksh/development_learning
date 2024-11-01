package com.pradyotprakash.glassbridgegame.app.pages.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.sharp.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pradyotprakash.glassbridgegame.app.pages.home.viewModel.HomeScreenViewModel
import glassbridgegame.composeapp.generated.resources.Res
import glassbridgegame.composeapp.generated.resources.app_name
import glassbridgegame.composeapp.generated.resources.lets_play
import glassbridgegame.composeapp.generated.resources.multiplayer
import glassbridgegame.composeapp.generated.resources.offline
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = viewModel(),
    navigateToBridgeGame: (Boolean) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = paddingValues.calculateStartPadding(LocalLayoutDirection.current) + 15.dp,
                    end = paddingValues.calculateEndPadding(LocalLayoutDirection.current) + 15.dp,
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding(),
                ),
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                stringResource(Res.string.lets_play),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.fillMaxWidth(),
            )
            Text(
                stringResource(Res.string.app_name),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(45.dp))
            IconButton(
                onClick = {},
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
            ) {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = Icons.Filled.PlayArrow.name,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    navigateToBridgeGame(true)
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    stringResource(Res.string.offline),
                )
            }
            FilledTonalButton(
                onClick = {
                    navigateToBridgeGame(false)
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    stringResource(Res.string.multiplayer),
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}