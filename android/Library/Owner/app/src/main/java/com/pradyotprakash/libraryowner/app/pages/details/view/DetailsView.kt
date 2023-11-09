package com.pradyotprakash.libraryowner.app.pages.details.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pradyotprakash.libraryowner.app.composables.CustomButton
import com.pradyotprakash.libraryowner.app.composables.PageStateComposable
import com.pradyotprakash.libraryowner.app.localization.TR
import com.pradyotprakash.libraryowner.app.pages.details.view.composables.LibraryDetailsItemComposable
import com.pradyotprakash.libraryowner.app.pages.details.view.composables.YourDetailsItemComposable
import com.pradyotprakash.libraryowner.app.pages.details.viewmodel.DetailsViewModel
import com.pradyotprakash.libraryowner.app.pages.details.viewmodel.utils.CustomerDetails

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsView(detailsViewModel: DetailsViewModel = hiltViewModel()) {
    val loading = detailsViewModel.loading.observeAsState(false)
    val error = detailsViewModel.error.observeAsState("")
    val customerDetails = detailsViewModel.customerDetails.observeAsState(CustomerDetails())
    val libraryDetailList = detailsViewModel.libraryDetails.observeAsState(emptyList())

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
                        profileImage = customerDetails.value.profileImage,
                        updateTextFieldValue = detailsViewModel::updateTextFieldValue,
                        imageSelector = detailsViewModel::openImagePicker
                    )
                }

                items(libraryDetailList.value.size) { index ->
                    val details = libraryDetailList.value[index]

                    LibraryDetailsItemComposable(
                        index = index,
                        name = details.name,
                        emailId = details.emailId,
                        phoneNumber = details.phoneNumber,
                        address = details.address,
                        showAddNewLibrary = index == libraryDetailList.value.lastIndex,
                        showDeleteLibrary = libraryDetailList.value.size > 1,
                        deleteLibraryInformation = detailsViewModel::deleteLibraryInformation,
                        addNewLibraryInformation = detailsViewModel::addNewLibraryInformation,
                        updateTextFieldValue = detailsViewModel::updateTextFieldValue,
                        emailIdSameAsCustomer = details.emailIdSameAsCustomer,
                        phoneNumberSameAsCustomer = details.phoneNumberSameAsCustomer,
                        onCheckedChange = detailsViewModel::onCheckedChange
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(20.dp))
                    CustomButton(
                        onClick = detailsViewModel::saveDetails,
                    ) {
                        Text(text = TR.saveDetails)
                    }
                }
            }
        }
    }
}