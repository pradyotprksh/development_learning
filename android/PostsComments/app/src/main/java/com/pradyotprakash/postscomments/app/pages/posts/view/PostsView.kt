package com.pradyotprakash.postscomments.app.pages.posts.view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pradyotprakash.postscomments.app.composables.ConfirmationDialog
import com.pradyotprakash.postscomments.app.composables.PageStateComposable
import com.pradyotprakash.postscomments.app.localization.TR
import com.pradyotprakash.postscomments.app.pages.posts.view.composables.PostDetailsComposable
import com.pradyotprakash.postscomments.app.pages.posts.viewmodel.PostsViewModel
import com.pradyotprakash.postscomments.domain.models.PostDetails

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostsView(
    postsViewModel: PostsViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        postsViewModel.getPosts()
    }

    val loading by postsViewModel.loading.observeAsState(false)
    val error by postsViewModel.error.observeAsState("")
    val confirmationDialog by postsViewModel.confirmationDialog.observeAsState(
        ConfirmationDialog()
    )
    val posts by postsViewModel.posts.observeAsState(emptyList())

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
                contentPadding = PaddingValues(
                    start = paddingValues.calculateStartPadding(LayoutDirection.Ltr) + 15.dp,
                    top = paddingValues.calculateTopPadding() + 15.dp,
                    end = paddingValues.calculateEndPadding(LayoutDirection.Ltr) + 15.dp,
                    bottom = paddingValues.calculateTopPadding()
                ),
            ) {
                items(posts) { post ->
                    PostDetailsComposable(
                        title = post.title,
                        text = post.text,
                        isFromCurrentUser = postsViewModel.isFromCurrentUser(post.createdBy)
                    )
                    Box(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}