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
import com.pradyotprakash.libraryowner.app.pages.details.viewmodel.utils.DetailsTextField

@Composable
fun YourDetailsItemComposable(
    name: String,
    emailId: String,
    phoneNumber: String,
    profileImage: String,
    updateTextFieldValue: (String, DetailsTextField) -> Unit,
    imageSelector: () -> Unit,
) {
    SectionComposable(
        title = TR.yourDetailsTitle,
        subtitle = TR.yourDetailsSubtitle
    ) {
        /*Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { imageSelector() }
        ) {
            CoilImage(
                imageModel = { profileImage },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.FillBounds,
                    alignment = Alignment.Center
                ),
                failure = {
                    Image(
                        painter = painterResource(id = Assets.DefaultProfileImage.resourceId),
                        contentDescription = Assets.DefaultProfileImage.imageDescription,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxSize()
                    )
                },
                loading = {
                    Image(
                        painter = painterResource(id = Assets.DefaultProfileImage.resourceId),
                        contentDescription = Assets.DefaultProfileImage.imageDescription,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxSize()
                    )
                },
                modifier = Modifier
                    .size(100.dp)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = CircleShape
                    ),
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = TR.profileImagePickerHelper,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(5.dp))*/
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