package com.project.pradyotprakash.whatsappcompose.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.project.pradyotprakash.whatsappcompose.ui.theme.black20Bold
import com.project.pradyotprakash.whatsappcompose.ui.theme.lightGray15
import com.project.pradyotprakash.whatsappcompose.ui.theme.link15

/**
 * A single card for the user to show the basic details.
 *
 * This will be used to show the current user about another user only the few details which are
 * required in a list mostly.
 */

@Composable
fun SingleUserCard(
    profileImage: String,
    title: String,
    subTitle: String = "",
    thirdLine: String = "",
    userId: String = "",
    userMessage: (String) -> Unit
) {
    Card(
        backgroundColor = Color.White,
        elevation = 3.dp,
        modifier = Modifier.fillMaxWidth().clickable {
            userMessage(userId)
        }
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CircularUrlImage(url = profileImage, size = 50)
            SizedBox(width = 10)
            Column {
                Text(text = title, style = black20Bold)
                if (subTitle.isNotEmpty() || thirdLine.isNotEmpty()) {
                    SizedBox(height = 5)
                }
                if (subTitle.isNotEmpty()) {
                    Text(text = subTitle, style = link15)
                }
                if (subTitle.isNotEmpty() || thirdLine.isNotEmpty()) {
                    SizedBox(height = 5)
                }
                if (thirdLine.isNotEmpty()) {
                    Text(text = thirdLine, style = lightGray15)
                }
            }
        }
    }
}