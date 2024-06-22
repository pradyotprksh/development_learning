package app.pages.directMessage.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.VideoCall
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.composables.LoadingDialogComposable
import app.composables.ProfileImageComposable
import app.pages.directMessage.viewModel.DirectMessageViewModel
import kotlinx.coroutines.launch
import utils.Localization

@OptIn(ExperimentalMaterial3Api::class)
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
    val replyFocusRequester = remember { FocusRequester() }
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

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {},
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = Icons.AutoMirrored.Filled.ArrowBack.name,
                        )
                    }
                },
                title = {
                    directMessageStateState.userInfo?.let { userInfo ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            ProfileImageComposable(
                                profileImage = userInfo.profilePicture,
                                modifier = Modifier.size(30.dp),
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = userInfo.name,
                                style = MaterialTheme.typography.titleMedium,
                            )
                        }
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
                }
            )
        }
    ) { }
}