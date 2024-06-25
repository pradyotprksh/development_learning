package app.pages.home.message.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.composables.BoxedCircularProgressBarIndicator
import app.composables.CircularDotComposable
import app.composables.ProfileImageComposable
import app.pages.home.message.viewModel.HomeMessageViewModel
import kotlinx.coroutines.launch
import utils.Constants.ConstValues.USERNAME_PREFIX
import utils.Localization

@Composable
fun HomeMessageScreen(
    homeMessageViewModel: HomeMessageViewModel = viewModel { HomeMessageViewModel() },
    openDirectMessage: (String?, String) -> Unit,
) {
    val homeMessageState by homeMessageViewModel.homeMessageState.collectAsState()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    homeMessageState.snackBarMessage?.let { message ->
        scope.launch {
            val result = snackbarHostState.showSnackbar(
                message = message,
                actionLabel = Localization.OKAY,
                duration = SnackbarDuration.Short
            )
            when (result) {
                SnackbarResult.ActionPerformed, SnackbarResult.Dismissed -> {
                    homeMessageViewModel.removeSnackBarMessage()
                }
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { paddingValues ->
        val startEndPaddingModifier = Modifier.padding(
            start = paddingValues.calculateStartPadding(LocalLayoutDirection.current) + 10.dp,
            end = paddingValues.calculateEndPadding(LocalLayoutDirection.current) + 10.dp,
        )

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding(),
                ).fillMaxSize()
            ) {
                items(homeMessageState.chats) { chat ->
                    val usersProfilePicture =
                        chat.users.filter { !it.isSameUser }.map { it.profilePicture }
                    val userNames =
                        chat.users.filter { !it.isSameUser }.joinToString(", ") { it.name }
                    val username =
                        if (usersProfilePicture.size == 1) chat.users.firstOrNull { !it.isSameUser }?.username else null
                    val lastMessage = chat.lastMessageDetails?.message
                    val messageOn = chat.lastMessageDetails?.messageTimeAgo
                    val userId =
                        if (usersProfilePicture.size == 1) chat.users.firstOrNull { !it.isSameUser }?.id else null

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = startEndPaddingModifier.fillMaxWidth().clickable {
                            openDirectMessage(userId, chat.chatId)
                        },
                    ) {
                        if (usersProfilePicture.size < 2) {
                            ProfileImageComposable(
                                profileImage = usersProfilePicture.firstOrNull(),
                                modifier = Modifier.size(60.dp),
                            )
                        } else {
                            Box(
                                modifier = Modifier.clip(
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
                                        ProfileImageComposable(
                                            profileImage = second,
                                            modifier = Modifier.weight(1f).fillMaxSize(),
                                        )
                                    }
                                    Row(
                                        modifier = Modifier.weight(1f),
                                    ) {
                                        ProfileImageComposable(
                                            profileImage = third,
                                            modifier = Modifier.weight(1f).fillMaxSize(),
                                        )
                                        ProfileImageComposable(
                                            profileImage = fourth,
                                            modifier = Modifier.weight(1f).fillMaxSize(),
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Column {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                            ) {
                                Text(
                                    userNames, style = MaterialTheme.typography.titleMedium
                                )
                                username?.let {
                                    Spacer(modifier = Modifier.width(2.dp))
                                    Text(
                                        "$USERNAME_PREFIX${username}",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                                Spacer(modifier = Modifier.width(4.dp))
                                CircularDotComposable(
                                    modifier = Modifier.size(3.dp),
                                )
                                messageOn?.let {
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        messageOn, style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                            lastMessage?.let {
                                Text(
                                    lastMessage, style = MaterialTheme.typography.bodyMedium,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                )
                            }
                        }
                    }
                }
            }

            BoxedCircularProgressBarIndicator(
                modifier = Modifier.align(Alignment.TopCenter),
                visible = homeMessageState.showLoading,
            )
        }
    }
}