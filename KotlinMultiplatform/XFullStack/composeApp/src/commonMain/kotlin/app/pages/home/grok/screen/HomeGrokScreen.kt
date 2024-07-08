package app.pages.home.grok.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import app.pages.home.grok.screen.composables.GrokChatTabComposable
import app.pages.home.grok.screen.composables.GrokMessageComposable
import app.pages.home.grok.screen.composables.GrokTextFieldComposable
import app.pages.home.grok.viewModel.HomeGrokViewModel
import kotlinx.coroutines.launch
import utils.Constants.ConstValues.MODEL
import utils.Localization

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeGrokScreen(
    isPhone: Boolean,
    homeGrokViewModel: HomeGrokViewModel = viewModel { HomeGrokViewModel() },
) {
    val homeGrokState by homeGrokViewModel.homeGrokState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    homeGrokState.snackBarMessage?.let { message ->
        scope.launch {
            val result = snackbarHostState.showSnackbar(
                message = message,
                actionLabel = Localization.OKAY,
                duration = SnackbarDuration.Short
            )
            when (result) {
                SnackbarResult.ActionPerformed, SnackbarResult.Dismissed -> {
                    homeGrokViewModel.removeSnackBarMessage()
                }
            }
        }
    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            GrokChatTabComposable(
                selectedIndex = homeGrokState.selectedIndex,
                size = homeGrokState.grokChats.size,
                getChatTitle = { index ->
                    homeGrokState.grokChats[index - 1].chatTitle
                },
                selectIndex = {
                    homeGrokViewModel.selectIndex(it)
                },
            )

            LazyColumn(
                modifier = Modifier.padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding(),
                ).weight(1f),
                reverseLayout = true,
            ) {
                items(homeGrokState.conversations) { conversation ->
                    GrokMessageComposable(
                        role = conversation.role,
                        message = conversation.message,
                        profileImage = homeGrokState.profileImage,
                        showLoader = false,
                    )
                }
            }

            if (homeGrokState.showLoading) {
                GrokMessageComposable(
                    role = MODEL,
                    message = "",
                    profileImage = homeGrokState.profileImage,
                    showLoader = true,
                )
            }

            GrokTextFieldComposable(
                value = homeGrokState.prompt,
                onValueChange = {
                    homeGrokViewModel.updatePrompt(it)
                },
                sendPrompt = {
                    homeGrokViewModel.sendPrompt()
                },
            )
        }
    }
}