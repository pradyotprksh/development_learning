package app.pages.createTweet.screen.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import utils.Constants.ConstValues.MAX_POLL_CHOICE_LENGTH
import utils.Localization

@Composable
fun PollChoiceInputComposable(
    modifier: Modifier = Modifier,
    pollChoices: List<String>,
    hour: Long?,
    minute: Long?,
    seconds: Long?,
    onPollCloseClick: () -> Unit,
    onAddNewPollClick: () -> Unit,
    onPollChoiceChange: (Int, String) -> Unit,
    onPollTimeChange: (Long?, Long?, Long?) -> Unit,
) {
    Column(
        modifier = modifier.border(
            width = Dp.Hairline,
            color = MaterialTheme.colorScheme.onBackground,
            shape = RoundedCornerShape(10.dp)
        ),
    ) {
        pollChoices.forEachIndexed { index, s ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                OutlinedTextField(
                    value = s,
                    onValueChange = {
                        onPollChoiceChange(index, it)
                    },
                    modifier = Modifier.padding(
                        start = 10.dp,
                        end = 10.dp,
                        bottom = 10.dp,
                    ).weight(1f),
                    label = {
                        Text(
                            Localization.format(
                                Localization.CHOICE,
                                index,
                            ),
                        )
                    },
                    suffix = {
                        Text(
                            "${MAX_POLL_CHOICE_LENGTH - s.length}",
                            style = MaterialTheme.typography.bodySmall,
                        )
                    },
                )
                if (index == 0) {
                    IconButton(
                        onClick = onPollCloseClick,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = Icons.Default.Close.name,
                        )
                    }
                } else {
                    IconButton(
                        onClick = {
                            if (index == pollChoices.lastIndex) onAddNewPollClick()
                        }, modifier = Modifier.alpha(
                            if (index == pollChoices.lastIndex) 1f else 0f,
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.AddCircle,
                            contentDescription = Icons.Default.AddCircle.name,
                        )
                    }
                }
            }
        }
        HorizontalDivider(
            thickness = Dp.Hairline,
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            OutlinedTextField(
                value = hour?.toString() ?: "",
                onValueChange = {
                    it.toLongOrNull()?.let { value ->
                        onPollTimeChange(value, minute, seconds)
                    }
                },
                label = {
                    Text(
                        Localization.HOURS.uppercase(),
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                ),
                modifier = Modifier.weight(1f).padding(
                    start = 10.dp,
                    bottom = 10.dp,
                ),
            )
            OutlinedTextField(
                value = minute?.toString() ?: "",
                onValueChange = {
                    it.toLongOrNull()?.let { value ->
                        onPollTimeChange(hour, value, seconds)
                    }
                },
                label = {
                    Text(
                        Localization.MINUTES.uppercase(),
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                ),
                modifier = Modifier.padding(
                    start = 10.dp,
                    end = 10.dp,
                    bottom = 10.dp,
                ).weight(1f),
            )
            OutlinedTextField(
                value = seconds?.toString() ?: "",
                onValueChange = {
                    it.toLongOrNull()?.let { value ->
                        onPollTimeChange(hour, minute, value)
                    }
                },
                label = {
                    Text(
                        Localization.SECONDS.uppercase(),
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                ),
                modifier = Modifier.weight(1f).padding(
                    end = 10.dp,
                    bottom = 10.dp,
                ),
            )
        }
    }
}