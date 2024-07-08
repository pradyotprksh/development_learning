package app.pages.home.grok.screen.composables

import androidx.compose.foundation.background
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.composables.ProfileImageComposable
import app.pages.home.bottomBar.HomeBottomNavItems
import utils.Constants.ConstValues.MODEL
import utils.Constants.ConstValues.USER

@Composable
fun GrokMessageComposable(
    modifier: Modifier = Modifier,
    role: String,
    message: String,
    profileImage: String?,
    showLoader: Boolean,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Spacer(modifier = Modifier.height(5.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            if (role == USER) {
                Spacer(modifier = Modifier.weight(1f))
            }

            if (role == MODEL) {
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    imageVector = HomeBottomNavItems.Grok.icon,
                    contentDescription = HomeBottomNavItems.Grok.icon.name,
                )
                Spacer(modifier = Modifier.width(5.dp))
            }

            if (showLoader) {
                CircularProgressIndicator()
            } else {
                Box(
                    modifier = Modifier.background(
                        color = if (role == USER) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.secondaryContainer,
                        shape = RoundedCornerShape(
                            topStart = 10.dp,
                            topEnd = 10.dp,
                            bottomStart = if (role == USER) 10.dp else 1.dp,
                            bottomEnd = if (role == USER) 1.dp else 10.dp,
                        ),
                    ).fillMaxWidth(0.6f)
                ) {
                    Text(
                        message,
                        modifier = Modifier.padding(
                            10.dp,
                        ).align(
                            if (role == USER) Alignment.TopEnd else Alignment.TopStart,
                        ).fillMaxWidth(),
                        textAlign = if (role == USER) TextAlign.End else TextAlign.Start,
                    )
                }
            }

            if (role == USER) {
                Spacer(modifier = Modifier.width(5.dp))
                ProfileImageComposable(
                    profileImage = profileImage,
                    modifier = Modifier.size(30.dp),
                )
                Spacer(modifier = Modifier.width(5.dp))
            }
        }

        Spacer(modifier = Modifier.height(5.dp))
    }
}