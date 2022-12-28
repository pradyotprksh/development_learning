package com.project.pradyotprakash.rental.app.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.pradyotprakash.rental.R
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.domain.modal.UserEntity

@Composable
fun UserDetailsComposable(
    userDetails: UserEntity,
    showOtherDetails: Boolean = false,
    onClick: ((String) -> Unit)? = null,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {
                onClick?.let {
                    it(userDetails.user_id)
                }
            }
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                NetworkImageComposable(
                    imageUrl = userDetails.profile_pic_url,
                    size = 50.dp,
                    cornerSize = 25.dp,
                    error = R.drawable.user_image
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "${userDetails.fullName} (${userDetails.user_type})",
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = String.format(
                    TR.addressColon,
                    userDetails.permanent_address.display_name
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = String.format(
                    TR.professionColon,
                    userDetails.profession
                )
            )
            if (showOtherDetails) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = String.format(TR.joinedAgo, userDetails.accountCreateOnTimeAgo))
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "${TR.emailAddress}: ${userDetails.email_address}")
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "${TR.phoneNumber}: ${userDetails.phone_number}")
            }
        }
    }
}