package com.project.pradyotprakash.twitter.twiteme.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.project.pradyotprakash.twitter.twiteme.R
import com.project.pradyotprakash.twitter.twiteme.theme.DodgeBlue
import com.project.pradyotprakash.twitter.utils.ImageContentDescription

@Composable
fun TwitterLoader(
    footerMessage: String? = null
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.size(70.dp),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator(
                color = DodgeBlue,
                strokeWidth = 3.dp,
                modifier = Modifier.fillMaxSize(),
            )
            Image(
                painter = painterResource(id = R.drawable.ic_twitter_logo),
                contentDescription = ImageContentDescription.TwitterLogo,
                modifier = Modifier.padding(20.dp),
            )
        }
        footerMessage?.let {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = it,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}
