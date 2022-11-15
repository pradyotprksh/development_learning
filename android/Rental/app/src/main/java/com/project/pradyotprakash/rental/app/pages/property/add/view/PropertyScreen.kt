package com.project.pradyotprakash.rental.app.pages.property.add.view

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
import com.project.pradyotprakash.rental.app.pages.property.add.viewmodel.PropertyViewModel

@Composable
fun PropertyScreen(
    propertyViewModel: PropertyViewModel = hiltViewModel(),
    propertyId: String,
) {
    LaunchedEffect(key1 = propertyId) {
        propertyViewModel.setInitialScreenDetails(propertyId)
    }

    val fields = propertyViewModel.fields.observeAsState(emptyList())
    val loading = propertyViewModel.loading.observeAsState(false)
    val error = propertyViewModel.error.observeAsState("")
    val propertyDetails = propertyViewModel.propertyDetails.observeAsState(null)

    PageStateComposable(
        isLoading = loading.value,
        errorMessage = error.value,
        dismissErrorAlert = propertyViewModel::updateErrorState
    ) {
        FieldComposable(
            fields = fields,
            onValueChange = propertyViewModel::updateFieldState,
            appBarText = {
                if (!loading.value) {
                    propertyDetails.value?.let {
                        Text(
                            text = String.format(
                                TR.editPropertyWithName,
                                it.property_name,
                            ),
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center,
                        )
                    } ?: run {
                        Text(
                            text = TR.addANewProperty,
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center,
                        )
                    }
                } else {
                    Text(
                        text = TR.loading,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                    )
                }
            },
            appBarNavigationIcon = Icons.Default.ArrowBack,
            appBarNavigationIconAction = propertyViewModel::navigateBack,
            bottomBar = {
                if (!loading.value) {
                    Button(
                        onClick = propertyViewModel::rentalPropertyDetails,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp)
                    ) {
                        Text(
                            text = if (propertyDetails.value != null) TR.update else TR.save
                        )
                    }
                }
            }
        )
    }
}