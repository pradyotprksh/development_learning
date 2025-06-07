package com.pradyotprakash.personalblog.app.pages.home.screen

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.pradyotprakash.personalblog.utils.Localization

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    isAdmin: Boolean,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        Localization.APP_NAME,
                    )
                },
                actions = {
                    if (isAdmin) {
                        IconButton(
                            onClick = {},
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = Icons.Default.Add.name,
                            )
                        }
                    }
                }
            )
        }
    ) { }
}