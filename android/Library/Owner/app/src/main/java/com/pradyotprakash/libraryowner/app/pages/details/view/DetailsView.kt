package com.pradyotprakash.libraryowner.app.pages.details.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pradyotprakash.libraryowner.app.composables.CustomButton
import com.pradyotprakash.libraryowner.app.composables.CustomOutlinedTextField
import com.pradyotprakash.libraryowner.app.composables.PageStateComposable
import com.pradyotprakash.libraryowner.app.localization.TR
import com.pradyotprakash.libraryowner.app.pages.details.view.composables.SectionComposable
import com.pradyotprakash.libraryowner.app.pages.details.view.composables.YourDetailsItemComposable
import com.pradyotprakash.libraryowner.app.pages.details.viewmodel.CustomerDetails
import com.pradyotprakash.libraryowner.app.pages.details.viewmodel.DetailsTextField
import com.pradyotprakash.libraryowner.app.pages.details.viewmodel.DetailsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsView(detailsViewModel: DetailsViewModel = hiltViewModel()) {
    val loading = detailsViewModel.loading.observeAsState(false)
    val error = detailsViewModel.error.observeAsState("")
    val customerDetails = detailsViewModel.customerDetails.observeAsState(CustomerDetails())

    PageStateComposable(
        isLoading = loading.value,
        errorMessage = error.value,
        dismissErrorAlert = detailsViewModel::updateErrorState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = TR.detailsTitle)
                    },
                    navigationIcon = {}
                )
            }
        ) { paddingValues ->
            LazyColumn(
                contentPadding = PaddingValues(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding(),
                    start = 15.dp,
                    end = 15.dp
                ),
            ) {
                item {
                    YourDetailsItemComposable(
                        name = customerDetails.value.name,
                        emailId = customerDetails.value.emailId,
                        phoneNumber = customerDetails.value.phoneNumber,
                        updateTextFieldValue = detailsViewModel::updateTextFieldValue
                    )
                }

                item {
                    SectionComposable(
                        title = TR.libraryDetailsTitle,
                        subtitle = TR.libraryDetailsSubtitle
                    ) {}
                    Spacer(modifier = Modifier.height(15.dp))
                }

                item {
                    Spacer(modifier = Modifier.height(20.dp))
                    CustomButton(
                        onClick = { },
                    ) {
                        Text(text = TR.saveDetails)
                    }
                }
            }
        }
    }
}