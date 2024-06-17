package app.composables.userAppBar.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.composables.ProfileImageComposable
import app.composables.XAppBarComposable
import app.composables.userAppBar.viewModel.UserAppBarViewModel

@Composable
fun UserAppBarComposable(
    modifier: Modifier = Modifier,
    userAppBarViewModel: UserAppBarViewModel = viewModel { UserAppBarViewModel() },
    title: @Composable (() -> Unit)? = null,
    toggleNavDrawer: () -> Unit,
) {
    LaunchedEffect(Unit) {
        userAppBarViewModel.initialSetup()
    }

    val userAppBarState by userAppBarViewModel.userAppBarState.collectAsState()

    XAppBarComposable(
        modifier = modifier,
        title = title,
        navigationIcon = {
            ProfileImageComposable(
                profileImage = userAppBarState.profileImage,
                modifier = Modifier.size(30.dp).clickable {
                    toggleNavDrawer()
                },
            )
        },
        actions = {
            IconButton(
                onClick = {},
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = Icons.Default.Settings.name,
                )
            }
        },
    )
}