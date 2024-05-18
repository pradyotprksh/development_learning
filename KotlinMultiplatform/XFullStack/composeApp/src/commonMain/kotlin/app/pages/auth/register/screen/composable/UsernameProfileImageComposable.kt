package app.pages.auth.register.screen.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import app.composables.UsernameTextFieldComposable
import app.pages.auth.register.state.RegisterState
import app.pages.auth.register.viewModel.RegisterViewModel
import utils.Localization
import utils.TextFieldType

@Composable
fun UsernameProfileImageComposable(
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
    }
}