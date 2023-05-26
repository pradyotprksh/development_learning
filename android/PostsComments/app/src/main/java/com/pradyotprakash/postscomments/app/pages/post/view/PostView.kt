package com.pradyotprakash.postscomments.app.pages.post.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.pradyotprakash.postscomments.app.composables.PageStateComposable
import com.pradyotprakash.postscomments.app.localization.TR
import com.pradyotprakash.postscomments.app.pages.post.viewmodel.PostViewModel
import com.pradyotprakash.postscomments.app.pages.posts.view.composables.PostDetailsComposable

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun PostView(
    postViewModel: PostViewModel = hiltViewModel(),
    postId: String,
) {
    LaunchedEffect(key1 = true) {
        postViewModel.getPostDetails(
            postId,
        )
    }

    val loading by postViewModel.loading.observeAsState(false)
    val error by postViewModel.error.observeAsState("")
    val title by postViewModel.title.observeAsState("")
    val text by postViewModel.text.observeAsState("")
    val comments by postViewModel.comments.observeAsState(emptyList())

    PageStateComposable(
        isLoading = loading,
        errorMessage = error,
        dismissErrorAlert = postViewModel::updateErrorState,
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(
                            onClick = postViewModel::goBack,
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = Icons.Default.ArrowBack.name,
                            )
                        }
                    }
                )
            },
        ) { paddingValues ->
            LazyColumn(
                contentPadding = PaddingValues(
                    start = paddingValues.calculateStartPadding(LayoutDirection.Ltr) + 15.dp,
                    top = paddingValues.calculateTopPadding() + 15.dp,
                    end = paddingValues.calculateEndPadding(LayoutDirection.Ltr) + 15.dp,
                    bottom = paddingValues.calculateTopPadding()
                ),
            ) {
                item {
                    PostDetailsComposable(
                        title = title,
                        text = text,
                        isFromCurrentUser = false,
                    )
                }

                stickyHeader {
                    Box(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.background)
                            .fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = TR.comments,
                                style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier.padding(
                                    top = 15.dp,
                                    bottom = 15.dp
                                )
                            )
                            TextButton(
                                onClick = {
                                    postViewModel.createComment(postId)
                                }
                            ) {
                                Text(text = TR.add)
                            }
                        }
                    }
                }

                items(comments) { comment ->
                    
                }
            }
        }
    }
}