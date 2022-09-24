package com.project.pradyotprakash.rental.app.pages.information.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.project.pradyotprakash.rental.app.composables.FieldComposable
import com.project.pradyotprakash.rental.app.composables.PageStateComposable
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.app.pages.information.viewmodel.InformationViewModel

/**
 * A screen for showing all the details related to the user,
 * if needed edit the details as well.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InformationScreen(
    informationViewModel: InformationViewModel
) {
    val fields = informationViewModel.fields.observeAsState(emptyList())
    val loading = informationViewModel.loading.observeAsState(false)
    val error = informationViewModel.error.observeAsState("")

    BackHandler {
        if (informationViewModel.allowBackOption) {
            informationViewModel.navigateBack()
        }
    }
    PageStateComposable(
        isLoading = loading.value,
        errorMessage = error.value,
        dismissErrorAlert = informationViewModel::updateErrorState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = TR.informationMessage,
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center,
                        )
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(),
                    navigationIcon = {
                        if (informationViewModel.allowBackOption) {
                            IconButton(onClick = informationViewModel::navigateBack) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = Icons.Default.ArrowBack.name,
                                )
                            }
                        }
                    },
                )
            },
            bottomBar = {
                Button(
                    onClick = {
                        informationViewModel.updateUserDetails()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                ) {
                    Text(text = TR.save)
                }
            }
        ) { paddingValues ->
            FieldComposable(
                paddingValues = paddingValues,
                fields = fields,
                onValueChange = informationViewModel::updateFieldState,
            )
        }
    }
}