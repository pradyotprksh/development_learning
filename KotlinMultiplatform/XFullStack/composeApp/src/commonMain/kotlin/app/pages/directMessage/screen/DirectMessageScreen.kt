package app.pages.directMessage.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import app.pages.directMessage.viewModel.DirectMessageViewModel

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
}