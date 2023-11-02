package com.pradyotprakash.libraryowner.app.pages.details.view.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.pradyotprakash.libraryowner.app.composables.CustomOutlinedTextField
import com.pradyotprakash.libraryowner.app.localization.TR
import com.pradyotprakash.libraryowner.app.pages.details.viewmodel.DetailsTextField

@Composable
fun YourDetailsItemComposable(
    name: String,
    emailId: String,
    phoneNumber: String,
    updateTextFieldValue: (String, DetailsTextField) -> Unit
) {
    SectionComposable(
        title = TR.yourDetailsTitle,
        subtitle = TR.yourDetailsSubtitle
    ) {
        CustomOutlinedTextField(
            value = name,
            onValueChange = { value ->
                updateTextFieldValue(
                    value,
                    DetailsTextField.CustomerName
                )
            },
            label = { Text(text = TR.name) },
            placeholder = { Text(text = TR.yourNamePlaceholder) },
            supportingText = { Text(text = TR.yourNameSupportingText) },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        CustomOutlinedTextField(
            value = emailId,
            onValueChange = { value ->
                updateTextFieldValue(
                    value,
                    DetailsTextField.CustomerEmailId
                )
            },
            label = { Text(text = TR.emailId) },
            placeholder = { Text(text = TR.emailIdPlaceholder) },
            supportingText = { Text(text = TR.emailIdSupportingText) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        CustomOutlinedTextField(
            value = phoneNumber,
            onValueChange = { value ->
                updateTextFieldValue(
                    value,
                    DetailsTextField.CustomerPhoneNumber
                )
            },
            label = { Text(text = TR.phoneNumber) },
            placeholder = { Text(text = TR.phoneNumberPlaceholder) },
            supportingText = { Text(text = TR.phoneNumberSupportingText) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            )
        )
    }
    Spacer(modifier = Modifier.height(15.dp))
}