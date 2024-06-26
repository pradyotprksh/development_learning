package app.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.pages.home.message.screen.composables.GroupProfileImagesComposable

@Composable
fun GroupUserImagesComposable(
    modifier: Modifier = Modifier,
    images: List<String>,
) {
    if (images.size < 2) {
        ProfileImageComposable(
            profileImage = images.firstOrNull(),
            modifier = modifier,
        )
    } else {
        GroupProfileImagesComposable(
            modifier = modifier,
            usersProfilePicture = images,
        )
    }
}