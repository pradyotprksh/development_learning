package app.composables.tweet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import app.composables.CircularDotComposable
import core.models.realm.PollChoicesDB
import utils.Localization

@Composable
fun PollChoicesComposable(
    modifier: Modifier = Modifier,
    pollChoices: List<PollChoicesDB>,
    isPollingAllowed: Boolean,
    pollingEndTime: String,
    totalVotesOnPoll: Int,
    onPollSelection: (String) -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        pollChoices.forEach { pollChoice ->
            val percentage =
                if (totalVotesOnPoll > 0) ((pollChoice.voteCount / totalVotesOnPoll).toFloat()) / 100 else 0f

            Column(
                modifier = Modifier.fillMaxWidth().padding(
                    vertical = 5.dp
                )
            ) {
                Box {
                    LinearProgressIndicator(
                        progress = { percentage },
                        strokeCap = StrokeCap.Round,
                        modifier = Modifier.height(35.dp).fillMaxWidth(),
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.align(Alignment.CenterStart).padding(
                            horizontal = 15.dp
                        ).fillMaxWidth(),
                    ) {
                        Text(
                            pollChoice.choice, modifier = Modifier.align(Alignment.CenterVertically)
                        )
                        Text(
                            "${percentage * 100}${Localization.PERCENTAGE}",
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                "$totalVotesOnPoll ${Localization.VOTES}",
                style = MaterialTheme.typography.bodySmall,
            )
            Spacer(modifier = Modifier.width(4.dp))
            CircularDotComposable(
                modifier = Modifier.size(3.dp),
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                pollingEndTime,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}