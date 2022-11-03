package com.project.pradyotprakash.rental.app.pages.information.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.pradyotprakash.rental.app.composables.PageStateComposable
import com.project.pradyotprakash.rental.app.composables.field.FieldComposable
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.app.pages.information.viewmodel.InformationViewModel

/**
 * A screen for showing all the details related to the user,
 * if needed edit the details as well.
 */
@Composable
fun InformationScreen(
    informationViewModel: InformationViewModel = hiltViewModel(),
    onlyPreview: Boolean,
    allowBackOption: Boolean,
    firstTimeAddingDetails: Boolean,
) {
    LaunchedEffect(onlyPreview, allowBackOption, firstTimeAddingDetails) {
        informationViewModel.start(
            onlyPreview, allowBackOption, firstTimeAddingDetails
        )
    }

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
        FieldComposable(
            fields = fields,
            onValueChange = informationViewModel::updateFieldState,
            appBarText = {
                Text(
                    text = TR.informationMessage,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                )
            },
            appBarNavigationIcon = if (allowBackOption) Icons.Default.ArrowBack else null,
            appBarNavigationIconAction = informationViewModel::navigateBack,
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
        )
    }
}