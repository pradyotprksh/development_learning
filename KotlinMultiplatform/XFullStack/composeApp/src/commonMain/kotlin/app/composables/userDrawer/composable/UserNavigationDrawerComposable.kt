package app.composables.userDrawer.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SpatialAudio
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.composables.AppIconComposable
import app.composables.FollowingFollowerComposable
import app.composables.ProfileImageComposable
import app.composables.userDrawer.viewModel.UserDrawerViewModel
import utils.Constants.ConstValues.USERNAME_PREFIX
import utils.Localization.BOOKMARKS
import utils.Localization.COMMUNITIES
import utils.Localization.LISTS
import utils.Localization.MONETISATION
import utils.Localization.PREMIUM
import utils.Localization.PROFILE
import utils.Localization.SPACES

@Composable
fun UserNavigationDrawerComposable(
    modifier: Modifier = Modifier,
    userDrawerViewModel: UserDrawerViewModel = viewModel { UserDrawerViewModel() },
    openProfileDetails: (String) -> Unit,
    openBookmarkPage: () -> Unit,
) {
    LaunchedEffect(Unit) {
        userDrawerViewModel.initialSetup()
    }

    val userDrawerState by userDrawerViewModel.userDrawerState.collectAsState()

    ModalDrawerSheet(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(
                all = 15.dp
            ).fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                ProfileImageComposable(
                    profileImage = userDrawerState.profileImage,
                    modifier = Modifier.size(45.dp).clickable {
                        openProfileDetails(userDrawerState.userId ?: "")
                    },
                )
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = Icons.Default.Menu.name,
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                userDrawerState.name, style = MaterialTheme.typography.titleMedium
            )
            Text(
                "${USERNAME_PREFIX}${userDrawerState.username}",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(10.dp))
            FollowingFollowerComposable(
                userDrawerState.following.toString(),
                userDrawerState.followers.toString(),
            )
        }
        HorizontalDivider()
        LazyColumn {
            item {
                NavigationDrawerItem(
                    label = {
                        Text(
                            PROFILE, style = MaterialTheme.typography.titleMedium
                        )
                    },
                    selected = false,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = Icons.Default.Person.name,
                        )
                    },
                    onClick = {
                        openProfileDetails(userDrawerState.userId ?: "")
                    },
                )
            }
            item {
                NavigationDrawerItem(
                    label = {
                        Text(
                            PREMIUM, style = MaterialTheme.typography.titleMedium
                        )
                    },
                    selected = false,
                    icon = {
                        AppIconComposable(
                            boxModifier = Modifier.size(18.dp)
                        )
                    },
                    onClick = {},
                )
            }
            item {
                NavigationDrawerItem(
                    label = {
                        Text(
                            COMMUNITIES, style = MaterialTheme.typography.titleMedium
                        )
                    },
                    selected = false,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.People,
                            contentDescription = Icons.Default.People.name,
                        )
                    },
                    onClick = {},
                )
            }
            item {
                NavigationDrawerItem(
                    label = {
                        Text(
                            BOOKMARKS, style = MaterialTheme.typography.titleMedium
                        )
                    },
                    selected = false,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Bookmarks,
                            contentDescription = Icons.Default.Bookmarks.name,
                        )
                    },
                    onClick = openBookmarkPage,
                )
            }
            item {
                NavigationDrawerItem(
                    label = {
                        Text(
                            LISTS, style = MaterialTheme.typography.titleMedium
                        )
                    },
                    selected = false,
                    icon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.List,
                            contentDescription = Icons.AutoMirrored.Filled.List.name,
                        )
                    },
                    onClick = {},
                )
            }
            item {
                NavigationDrawerItem(
                    label = {
                        Text(
                            SPACES, style = MaterialTheme.typography.titleMedium
                        )
                    },
                    selected = false,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.SpatialAudio,
                            contentDescription = Icons.Default.SpatialAudio.name,
                        )
                    },
                    onClick = {},
                )
            }
            item {
                NavigationDrawerItem(
                    label = {
                        Text(
                            MONETISATION, style = MaterialTheme.typography.titleMedium
                        )
                    },
                    selected = false,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.MonetizationOn,
                            contentDescription = Icons.Default.MonetizationOn.name,
                        )
                    },
                    onClick = {},
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        HorizontalDivider()
        IconButton(
            onClick = {}, modifier = Modifier.padding(
                all = 10.dp
            )
        ) {
            Icon(
                imageVector = Icons.Default.DarkMode,
                contentDescription = Icons.Default.DarkMode.name,
            )
        }
    }
}