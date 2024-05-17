package app.pages.auth.register.screen.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.pages.auth.register.state.RegisterState
import app.pages.auth.register.viewModel.RegisterViewModel
import utils.Localization

@Composable
fun OtpVerificationComposable(
    modifier: Modifier = Modifier,
    startEndPaddingModifier: Modifier = Modifier,
    registerScreenState: RegisterState,
    registerViewModel: RegisterViewModel,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            Localization.WE_SENT_YOU_CODE,
            style = MaterialTheme.typography.headlineSmall,
            modifier = startEndPaddingModifier
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            Localization.ENTER_BELOW_TO_VERIFY.replace(
                "%s", registerScreenState.phoneEmailValue
            ), style = MaterialTheme.typography.bodySmall, modifier = startEndPaddingModifier
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = {
                Text(
                    if (registerScreenState.isUsingPhoneNumber) Localization.WAITING_FOR_SMS
                    else Localization.VERIFICATION_CODE
                )
            },
            modifier = startEndPaddingModifier.fillMaxWidth(),
            maxLines = 1,
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextButton(
            onClick = {},
            modifier = startEndPaddingModifier,
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                if (registerScreenState.isUsingPhoneNumber) Localization.DID_NOT_RECEIVE_SMS
                else Localization.DID_NOT_RECEIVE_EMAIL
            )
        }
    }
}