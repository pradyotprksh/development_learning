package com.pradyotprakash.postscomments.app.pages.postForm.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pradyotprakash.postscomments.app.composables.PageStateComposable
import com.pradyotprakash.postscomments.app.localization.TR
import com.pradyotprakash.postscomments.app.pages.postForm.viewmodel.PostFormViewModel
import com.pradyotprakash.postscomments.core.utils.PostArguments

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostFormView(
    postFormViewModel: PostFormViewModel = hiltViewModel(),
    postType: String,
    postId: String,
) {
    LaunchedEffect(key1 = true) {
        postFormViewModel.getPostDetails(
            postId,
            postType,
        )
    }

    val loading by postFormViewModel.loading.observeAsState(false)
    val enableSend by postFormViewModel.enableSend.observeAsState(false)
    val error by postFormViewModel.error.observeAsState("")
    val title by postFormViewModel.title.observeAsState("")
    val text by postFormViewModel.text.observeAsState("")

    PageStateComposable(
        isLoading = loading,
        errorMessage = error,
        dismissErrorAlert = postFormViewModel::updateErrorState,
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
                            onClick = postFormViewModel::goToPostsScreen,
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
                modifier = Modifier
                    .padding(
                        start = paddingValues.calculateStartPadding(LayoutDirection.Ltr) + 15.dp,
                        top = paddingValues.calculateTopPadding() + 15.dp,
                        bottom = paddingValues.calculateBottomPadding() + 15.dp,
                        end = paddingValues.calculateRightPadding(LayoutDirection.Ltr) + 15.dp
                    )
                    .fillMaxSize()
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = {
                        postFormViewModel.updateValue(it, PostFormViewModel.FiledType.Title)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = TR.title)
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    singleLine = true,
                )
                Box(modifier = Modifier.height(15.dp))
                OutlinedTextField(
                    value = text,
                    onValueChange = {
                        postFormViewModel.updateValue(it, PostFormViewModel.FiledType.Text)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = TR.text)
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    maxLines = 15
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = {
                        postFormViewModel.sendPost(postId)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = enableSend
                ) {
                    Text(text = TR.send)
                }
            }
        }
    }
}