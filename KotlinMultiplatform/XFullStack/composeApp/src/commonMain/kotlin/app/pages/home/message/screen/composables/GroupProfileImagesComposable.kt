package app.pages.home.message.screen.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.composables.ProfileImageComposable

@Composable
fun GroupProfileImagesComposable(
    modifier: Modifier = Modifier,
    usersProfilePicture: List<String>,
) {
    Box(
        modifier = modifier.clip(
            CircleShape,
        ).border(
            width = Dp.Hairline,
            color = MaterialTheme.colorScheme.onBackground,
            shape = CircleShape,
        ).size(60.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            val first = usersProfilePicture.getOrNull(0)
            val second = usersProfilePicture.getOrNull(1)
            val third = usersProfilePicture.getOrNull(2)
            val fourth = usersProfilePicture.getOrNull(3)

            Row(
                modifier = Modifier.weight(1f),
            ) {
                ProfileImageComposable(
                    profileImage = first,
                    modifier = Modifier.weight(1f).fillMaxSize(),
                )
                second?.let {
                    ProfileImageComposable(
                        profileImage = second,
                        modifier = Modifier.weight(1f).fillMaxSize(),
                    )
                }
            }
            third?.let {
                Row(
                    modifier = Modifier.weight(1f),
                ) {
                    ProfileImageComposable(
                        profileImage = third,
                        modifier = Modifier.weight(1f).fillMaxSize(),
                    )
                    fourth?.let {
                        ProfileImageComposable(
                            profileImage = fourth,
                            modifier = Modifier.weight(1f).fillMaxSize(),
                        )
                    }
                }
            }
        }
    }
}