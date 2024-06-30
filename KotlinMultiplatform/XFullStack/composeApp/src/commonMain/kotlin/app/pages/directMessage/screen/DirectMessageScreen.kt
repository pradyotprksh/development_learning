package app.pages.directMessage.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.VideoCall
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.composables.GroupUserImagesComposable
import app.composables.LoadingDialogComposable
import app.pages.directMessage.screen.composables.MessageComposable
import app.pages.directMessage.screen.composables.MessageFieldComposable
import app.pages.directMessage.viewModel.DirectMessageViewModel
import kotlinx.coroutines.launch
import utils.Localization

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun DirectMessageScreen(
    directMessageViewModel: DirectMessageViewModel = viewModel { DirectMessageViewModel() },
    userId: String?,
    chatId: String?,
    openProfileDetails: (String) -> Unit,
    onNavigateBack: () -> Unit,
) {
    LaunchedEffect(userId, chatId) {
        directMessageViewModel.loadDetails(userId, chatId)
    }

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val directMessageStateState by directMessageViewModel.directMessageStateState.collectAsState()
    if (directMessageStateState.showLoading) {
        LoadingDialogComposable()
    }
    directMessageStateState.snackBarMessage?.let { message ->
        scope.launch {
            val result = snackbarHostState.showSnackbar(
                message = message,
                actionLabel = Localization.OKAY,
                duration = SnackbarDuration.Short
            )
            when (result) {
                SnackbarResult.ActionPerformed, SnackbarResult.Dismissed -> {
                    directMessageViewModel.removeSnackBarMessage()
                }
            }
        }
    }

    val otherUsers = directMessageStateState.usersInfo.filter { !it.isSameUser }
    val usersProfilePicture = otherUsers.map { it.profilePicture ?: "" }
    val userNames = otherUsers.joinToString(", ") { it.name }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack,
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = Icons.AutoMirrored.Filled.ArrowBack.name,
                        )
                    }
                },
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        GroupUserImagesComposable(
                            modifier = Modifier.size(30.dp),
                            images = usersProfilePicture,
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            userNames, style = MaterialTheme.typography.titleMedium
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {},
                    ) {
                        Icon(
                            imageVector = Icons.Default.VideoCall,
                            contentDescription = Icons.Default.VideoCall.name,
                        )
                    }
                    IconButton(
                        onClick = {},
                    ) {
                        Icon(
                            imageVector = Icons.Default.Call,
                            contentDescription = Icons.Default.Call.name,
                        )
                    }
                },
            )
        },
    ) { paddingValues ->
        val startEndPaddingModifier = Modifier.padding(
            start = paddingValues.calculateStartPadding(LocalLayoutDirection.current) + 25.dp,
            end = paddingValues.calculateEndPadding(LocalLayoutDirection.current) + 25.dp,
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding(),
            ),
            state = rememberLazyListState(),
            reverseLayout = true,
        ) {
            item {
                MessageFieldComposable(
                    value = directMessageStateState.message,
                    isFocused = directMessageStateState.isFocused,
                    onValueChange = { value -> directMessageViewModel.messageValueChange(value) },
                    onFocusChange = { isFocused -> directMessageViewModel.changeFocusValue(isFocused) },
                    onImageOptionSelect = {},
                    onGifOptionSelect = {},
                    onAudioOptionSelect = {},
                    sendMessage = {
                        directMessageViewModel.sendMessage()
                    },
                    modifier = Modifier.padding(
                        horizontal = 10.dp,
                    ),
                )
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }

            items(directMessageStateState.messages) { message ->
                message.group?.let {
                    Text(
                        it,
                        style = MaterialTheme.typography.titleSmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().padding(
                            vertical = 5.dp,
                        ),
                    )
                }

                message.messageResponse?.let {
                    MessageComposable(
                        messageResponse = it,
                    )
                }
            }

            stickyHeader {
                HorizontalDivider()
            }

            item {
                Column(
                    modifier = startEndPaddingModifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    GroupUserImagesComposable(
                        modifier = Modifier.size(60.dp),
                        images = usersProfilePicture,
                    )
                    Text(
                        text = userNames,
                        style = MaterialTheme.typography.titleMedium,
                    )
                    if (otherUsers.size == 1) {
                        Text(
                            text = otherUsers.first().username,
                            style = MaterialTheme.typography.bodySmall,
                        )
                        otherUsers.first().bio?.let { bio ->
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = bio,
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "${otherUsers.first().followers} ${Localization.FOLLOWERS}",
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}