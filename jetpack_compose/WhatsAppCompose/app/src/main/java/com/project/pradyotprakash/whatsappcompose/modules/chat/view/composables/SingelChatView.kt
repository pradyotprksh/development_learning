package com.project.pradyotprakash.whatsappcompose.modules.chat.view.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.project.pradyotprakash.whatsappcompose.R
import com.project.pradyotprakash.whatsappcompose.models.ChatDetails
import com.project.pradyotprakash.whatsappcompose.ui.composables.SizedBox
import com.project.pradyotprakash.whatsappcompose.ui.theme.Notification
import com.project.pradyotprakash.whatsappcompose.ui.theme.black20Bold
import com.project.pradyotprakash.whatsappcompose.ui.theme.gray15
import com.project.pradyotprakash.whatsappcompose.ui.theme.lightGray15

/**
 * A composable which will be used to show the ui of the single chat. With few details.
 */

@Composable
fun SingleChatView(
    userMessage: (String) -> Unit,
    singleChat: ChatDetails,
) {
    SizedBox(height = 5)
    Card(
        elevation = 5.dp,
        backgroundColor = Color.White,
        border = if (singleChat.isLastMessageRead) BorderStroke(
            width = 1.dp,
            color = Notification
        ) else null,
        modifier = Modifier.clickable {

        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(0.8f)
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = "otherUserDetails.profilePic",
                        builder = {
                            crossfade(true)
                        }
                    ),
                    contentDescription = stringResource(id = R.string.image_description_network),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(70.dp)
                )
                SizedBox(width = 10)
                Column {
                    Text(
                        text = "otherUserDetails.name",
                        style = black20Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    SizedBox(height = 2)
                    if (singleChat.isLastMessageByCurrentUser) {
                        Text(
                            text = "${stringResource(id = R.string.you)} ${singleChat.lastMessage}",
                            style = gray15,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    } else {
                        Text(
                            text = singleChat.lastMessage,
                            style = gray15,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
            SizedBox(width = 10)
            Text(
                text = singleChat.lastMessageSentOnString,
                style = lightGray15,
                modifier = Modifier
                    .padding(end = 15.dp)
                    .weight(0.2f),
                textAlign = TextAlign.End
            )
        }
    }
    SizedBox(height = 5)
}