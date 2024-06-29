package app.pages.directMessage.screen.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddReaction
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import core.models.response.MessageResponse

@Composable
fun MessageComposable(
    modifier: Modifier = Modifier,
    messageResponse: MessageResponse,
) {
    Column(
        modifier = modifier.padding(
            horizontal = 5.dp,
            vertical = 5.dp,
        ).fillMaxWidth(),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            if (messageResponse.isSendByCurrentUser) {
                Spacer(modifier = Modifier.weight(1f))
            }

            Box(
                modifier = Modifier.background(
                    color = if (messageResponse.isSendByCurrentUser) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(
                        topStart = 10.dp,
                        topEnd = 10.dp,
                        bottomStart = if (messageResponse.isSendByCurrentUser) 10.dp else 1.dp,
                        bottomEnd = if (messageResponse.isSendByCurrentUser) 1.dp else 10.dp,
                    ),
                ).fillMaxWidth(0.6f)
            ) {
                Text(
                    messageResponse.message,
                    modifier = Modifier.padding(
                        10.dp,
                    ).align(
                        if (messageResponse.isSendByCurrentUser) Alignment.TopEnd else Alignment.TopStart,
                    ).fillMaxWidth(),
                    textAlign = if (messageResponse.isSendByCurrentUser) TextAlign.End else TextAlign.Start,
                )
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            if (messageResponse.isSendByCurrentUser) {
                Spacer(modifier = Modifier.weight(1f))
            }

            Text(
                messageResponse.messageTime,
                style = MaterialTheme.typography.bodySmall,
                textAlign = if (messageResponse.isSendByCurrentUser) TextAlign.End else TextAlign.Start,
            )
            Spacer(modifier = Modifier.width(5.dp))
            Icon(
                imageVector = Icons.Default.AddReaction,
                contentDescription = Icons.Default.AddReaction.name,
                modifier = Modifier.size(15.dp).clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                ) { }
            )
        }
    }
}