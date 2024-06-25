package app.pages.home.message.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.composables.BoxedCircularProgressBarIndicator
import app.pages.home.message.screen.composables.ChatDetailsComposable
import app.pages.home.message.viewModel.HomeMessageViewModel
import kotlinx.coroutines.launch
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
                        chat.users.filter { !it.isSameUser }.map { it.profilePicture ?: "" }
                    val userNames =
                        chat.users.filter { !it.isSameUser }.joinToString(", ") { it.name }
                    val username =
                        if (usersProfilePicture.size == 1) chat.users.firstOrNull { !it.isSameUser }?.username else null
                    val lastMessage = chat.lastMessageDetails?.message
                    val messageOn = chat.lastMessageDetails?.messageTimeAgo
                    val userId =
                        if (usersProfilePicture.size == 1) chat.users.firstOrNull { !it.isSameUser }?.id else null

                    ChatDetailsComposable(
                        modifier = startEndPaddingModifier,
                        usersProfilePicture = usersProfilePicture,
                        userNames = userNames,
                        username = username,
                        lastMessage = lastMessage,
                        messageOn = messageOn,
                        openDirectMessage = {
                            openDirectMessage(userId, chat.chatId)
                        },
                    )
                }
            }

            BoxedCircularProgressBarIndicator(
                modifier = Modifier.align(Alignment.TopCenter),
                visible = homeMessageState.showLoading,
            )
        }
    }
}