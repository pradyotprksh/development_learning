package com.pradyotprakash.pitgull.app.pages.pullrequests.view.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pradyotprakash.pitgull.app.localizations.TR
import com.pradyotprakash.pitgull.app.utils.DateFormatter
import com.pradyotprakash.pitgull.domain.model.PullRequest

@Composable
fun PrDetails(
    prDetails: PullRequest
) {
    prDetails.user?.let { user ->
        UserDetails(user = user)
    }
    prDetails.title?.let {
        Text(
            text = it,
            style = MaterialTheme.typography.titleMedium
        )
    }
    prDetails.body?.let {
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = it,
            style = MaterialTheme.typography.bodySmall
        )
    }
    Spacer(modifier = Modifier.height(5.dp))
    Divider()
    Spacer(modifier = Modifier.height(5.dp))
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        prDetails.created_at?.let {
            Text(
                text = "${TR.createdOn} ${DateFormatter.convertToReadableDate(it)}",
                style = MaterialTheme.typography.bodySmall
            )
        }
        prDetails.closed_at?.let {
            Text(
                text = "${TR.closedOn} ${DateFormatter.convertToReadableDate(it)}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}