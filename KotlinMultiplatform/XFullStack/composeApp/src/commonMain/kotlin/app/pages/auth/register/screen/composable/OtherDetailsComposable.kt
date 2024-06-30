package app.pages.auth.register.screen.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.composables.ProfileImageComposable
import app.composables.UsernameTextFieldComposable
import app.pages.auth.register.state.RegisterState
import app.pages.auth.register.viewModel.RegisterViewModel
import utils.Constants.ConstValues.BIO_MAX_LENGTH
import utils.Constants.ConstValues.USER_PROFILE_IMAGE
import utils.Localization
import utils.TextFieldType

@Composable
fun OtherDetailsComposable(
    modifier: Modifier = Modifier,
    startEndPaddingModifier: Modifier = Modifier,
    registerScreenState: RegisterState,
    registerViewModel: RegisterViewModel,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            Localization.SHARE_PROFILE_IMAGE_USERNAME,
            style = MaterialTheme.typography.headlineSmall,
            modifier = startEndPaddingModifier
        )
        Spacer(modifier = Modifier.height(10.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            ProfileImageComposable(
                profileImage = registerScreenState.profileImageValue,
                modifier = Modifier.size(
                    120.dp,
                ).align(Alignment.Center).clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = {
                        registerViewModel.openFilePicker(
                            Localization.SELECT_PROFILE_IMAGE,
                            USER_PROFILE_IMAGE,
                        )
                    },
                ),
            )

            CircularProgressIndicator(
                modifier = Modifier.size(
                    120.dp,
                ).align(Alignment.Center),
                progress = { registerScreenState.profileImageProgress.toFloat() }
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        UsernameTextFieldComposable(
            modifier = startEndPaddingModifier,
            usernameValue = registerScreenState.usernameValue,
            isUsernameValid = registerScreenState.isUsernameValid,
            imageAction = ImeAction.Next,
            onValueChange = { type, value ->
                registerViewModel.updateTextField(
                    type, value
                )
            }
        )
        Spacer(modifier = Modifier.height(15.dp))
        OutlinedTextField(
            value = registerScreenState.bioValue ?: "",
            onValueChange = { value ->
                registerViewModel.updateTextField(
                    textFieldType = TextFieldType.Bio,
                    value
                )
            },
            label = {
                Text(
                    Localization.BIO,
                )
            },
            modifier = startEndPaddingModifier.fillMaxWidth(),
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
            ),
            supportingText = {
                Text(
                    text = "${registerScreenState.bioValue?.length ?: 0} / $BIO_MAX_LENGTH",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                )
            },
            trailingIcon = {
                if (registerScreenState.isBioValid) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = Icons.Default.CheckCircle.name,
                        tint = Color.Green
                    )
                }
            },
        )
    }
}