package com.pradyotprakash.postscomments.app.pages.posts.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pradyotprakash.postscomments.app.composables.ConfirmationDialog
import com.pradyotprakash.postscomments.app.composables.PageStateComposable
import com.pradyotprakash.postscomments.app.localization.TR
import com.pradyotprakash.postscomments.app.pages.posts.viewmodel.PostsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostsView(
    postsViewModel: PostsViewModel = hiltViewModel()
) {
    val loading by postsViewModel.loading.observeAsState(false)
    val error by postsViewModel.error.observeAsState("")
    val confirmationDialog by postsViewModel.confirmationDialog.observeAsState(
        ConfirmationDialog()
    )

    PageStateComposable(
        isLoading = loading,
        errorMessage = error,
        dismissErrorAlert = postsViewModel::updateErrorState,
        confirmationDialog = confirmationDialog
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = TR.appName)
                    },
                    actions = {
                        IconButton(
                            onClick = postsViewModel::confirmLogOutUser
                        ) {
                            Icon(
                                imageVector = Icons.Default.ExitToApp,
                                contentDescription = Icons.Default.ExitToApp.name,
                            )
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = postsViewModel::openCreatePostScreen,
                ) {
                    Row(
                        modifier = Modifier.padding(
                            all = 15.dp
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = Icons.Default.Edit.name,
                        )
                        Box(modifier = Modifier.width(5.dp))
                        Text(text = TR.createPost)
                    }
                }
            }
        ) { paddingValues ->
            LazyColumn(
                contentPadding = paddingValues,
            ) {}
        }
    }
}