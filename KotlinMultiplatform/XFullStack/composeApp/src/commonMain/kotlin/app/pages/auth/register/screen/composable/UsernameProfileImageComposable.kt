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
        OutlinedTextField(
            value = registerScreenState.usernameValue,
            onValueChange = { value ->
                registerViewModel.updateTextField(
                    textFieldType = TextFieldType.Username,
                    value
                )
            },
            label = {
                Text(
                    Localization.USERNAME
                )
            },
            modifier = startEndPaddingModifier.fillMaxWidth(),
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                capitalization = KeyboardCapitalization.None,
            ),
            trailingIcon = {
                if (registerScreenState.isUsernameValid) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = Icons.Default.CheckCircle.name,
                        tint = Color.Green
                    )
                }
            }
        )
    }
}