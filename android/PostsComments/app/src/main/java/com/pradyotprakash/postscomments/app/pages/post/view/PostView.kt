package com.pradyotprakash.postscomments.app.pages.post.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.hilt.navigation.compose.hiltViewModel
import com.pradyotprakash.postscomments.app.composables.PageStateComposable
import com.pradyotprakash.postscomments.app.localization.TR
import com.pradyotprakash.postscomments.app.pages.post.viewmodel.PostViewModel
import com.pradyotprakash.postscomments.core.utils.PostArguments

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostView(
    postViewModel: PostViewModel = hiltViewModel(),
    postType: String,
    postId: String,
) {
    val loading by postViewModel.loading.observeAsState(false)
    val error by postViewModel.error.observeAsState("")

    PageStateComposable(
        isLoading = loading,
        errorMessage = error,
        dismissErrorAlert = postViewModel::updateErrorState,
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = if (postType == PostArguments.createPost)
                                TR.createPost
                            else
                                TR.postEditing
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = postViewModel::goToPostsScreen,
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
            Column(
                modifier = Modifier.padding(
                    start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding(),
                    end = paddingValues.calculateRightPadding(LayoutDirection.Ltr)
                )
            ) {

            }
        }
    }
}