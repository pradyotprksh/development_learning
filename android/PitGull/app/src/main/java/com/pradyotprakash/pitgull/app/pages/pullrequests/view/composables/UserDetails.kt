package com.pradyotprakash.pitgull.app.pages.pullrequests.view.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pradyotprakash.pitgull.app.composables.NetworkImageComposable
import com.pradyotprakash.pitgull.domain.model.User

@Composable
fun UserDetails(
    user: User
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        NetworkImageComposable(
            imageUrl = user.avatar_url ?: "",
            size = 30.dp,
            cornerSize = 15.dp
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = user.login ?: "")
    }
    Spacer(modifier = Modifier.height(5.dp))
    Divider()
    Spacer(modifier = Modifier.height(5.dp))
}