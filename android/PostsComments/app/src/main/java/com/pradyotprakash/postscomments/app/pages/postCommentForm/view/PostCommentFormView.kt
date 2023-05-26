package com.pradyotprakash.postscomments.app.pages.postCommentForm.view

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
import com.pradyotprakash.postscomments.app.pages.postCommentForm.viewmodel.PostCommentFormViewModel
import com.pradyotprakash.postscomments.core.utils.PostCommentFormArguments

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostFormView(
    postCommentFormViewModel: PostCommentFormViewModel = hiltViewModel(),
    formType: String,
    formAction: String,
    commentId: String,
    postId: String,
) {
    LaunchedEffect(key1 = true) {
        postCommentFormViewModel.getPostCommentDetails(
            formType,
            formAction,
            commentId,
            postId,
        )
    }

    val loading by postCommentFormViewModel.loading.observeAsState(false)
    val enableSend by postCommentFormViewModel.enableSend.observeAsState(false)
    val error by postCommentFormViewModel.error.observeAsState("")
    val title by postCommentFormViewModel.title.observeAsState("")
    val text by postCommentFormViewModel.text.observeAsState("")

    PageStateComposable(
        isLoading = loading,
        errorMessage = error,
        dismissErrorAlert = postCommentFormViewModel::updateErrorState,
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = if (formType == PostCommentFormArguments.postForm)
                                        if (formAction == PostCommentFormArguments.create)
                                            TR.createPost
                                        else
                                            TR.postEditing
                                    else
                                        if (formAction == PostCommentFormArguments.create)
                                            TR.createComment
                                        else
                                            TR.commentEditing
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = postCommentFormViewModel::goBack,
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
                if (formType == PostCommentFormArguments.postForm)
                OutlinedTextField(
                    value = title,
                    onValueChange = {
                        postCommentFormViewModel.updateValue(
                            it,
                            PostCommentFormViewModel.FiledType.Title,
                            formType,
                        )
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
                        postCommentFormViewModel.updateValue(
                            it,
                            PostCommentFormViewModel.FiledType.Text,
                            formType,
                        )
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
                        postCommentFormViewModel.sendPostComment(
                            postId, commentId, formType
                        )
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