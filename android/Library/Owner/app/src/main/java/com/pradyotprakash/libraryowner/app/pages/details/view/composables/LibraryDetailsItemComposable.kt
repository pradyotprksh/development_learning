package com.pradyotprakash.libraryowner.app.pages.details.view.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.pradyotprakash.libraryowner.app.composables.CustomButton
import com.pradyotprakash.libraryowner.app.composables.CustomOutlinedTextField
import com.pradyotprakash.libraryowner.app.localization.TR
import com.pradyotprakash.libraryowner.app.pages.details.viewmodel.utils.DetailsTextField

@Composable
fun LibraryDetailsItemComposable(
    index: Int,
    name: String,
    emailId: String,
    phoneNumber: String,
    address: String,
    showAddNewLibrary: Boolean,
    showDeleteLibrary: Boolean,
    deleteLibraryInformation: (Int) -> Unit,
    addNewLibraryInformation: () -> Unit,
    updateTextFieldValue: (String, DetailsTextField, Int) -> Unit,
) {
    SectionComposable(
        title = "${index + 1}. ${TR.libraryDetailsTitle}",
        subtitle = TR.libraryDetailsSubtitle
    ) {
        CustomOutlinedTextField(
            value = name,
            onValueChange = { value -> updateTextFieldValue(value, DetailsTextField.LibraryName, index) },
            label = { Text(text = TR.libraryName) },
            placeholder = { Text(text = TR.libraryNamePlaceholder) },
            supportingText = { Text(text = TR.libraryNameSupportingText) },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        CustomOutlinedTextField(
            value = emailId,
            onValueChange = { value -> updateTextFieldValue(value, DetailsTextField.LibraryEmailId, index) },
            label = { Text(text = TR.libraryEmailId) },
            placeholder = { Text(text = TR.libraryEmailIdPlaceholder) },
            supportingText = { Text(text = TR.libraryEmailIdSupportingText) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        CustomOutlinedTextField(
            value = phoneNumber,
            onValueChange = { value -> updateTextFieldValue(value, DetailsTextField.LibraryPhoneNumber, index) },
            label = { Text(text = TR.libraryPhoneNumber) },
            placeholder = { Text(text = TR.libraryPhoneNumberPlaceholder) },
            supportingText = { Text(text = TR.libraryPhoneNumberSupportingText) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        CustomOutlinedTextField(
            value = address,
            onValueChange = { value -> updateTextFieldValue(value, DetailsTextField.LibraryAddress, index) },
            label = { Text(text = TR.libraryAddress) },
            placeholder = { Text(text = TR.libraryAddressPlaceholder) },
            supportingText = { Text(text = TR.libraryAddressSupportingText) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        if (showDeleteLibrary) {
            Spacer(modifier = Modifier.height(2.dp))
            CustomButton(
                color = MaterialTheme.colorScheme.error,
                onClick = { deleteLibraryInformation(index) },
            ) {
                Text(text = TR.delete)
            }
        }
        if (showAddNewLibrary) {
            Spacer(modifier = Modifier.height(2.dp))
            CustomButton(
                onClick = addNewLibraryInformation,
            ) {
                Text(text = TR.addNewLibrary)
            }
        }
    }
    Spacer(modifier = Modifier.height(5.dp))
}