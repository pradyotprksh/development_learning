package com.pradyotprakash.futuresugoroku.ui.pages.home.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pradyotprakash.futuresugoroku.R

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    goToGameScreen: () -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(
                paddingValues = innerPadding,
            ).fillMaxSize()
        ) {
            Text(
                text = stringResource(R.string.welcome_to),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(
                    top = 15.dp,
                    bottom = 15.dp,
                )
            )
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineLarge,
            )
            Spacer(modifier = Modifier.weight(1f))
            ElevatedButton(
                onClick = goToGameScreen,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 15.dp,
                    ),
            ) {
                Text(
                    text = stringResource(R.string.start_game),
                )
            }
        }
    }
}