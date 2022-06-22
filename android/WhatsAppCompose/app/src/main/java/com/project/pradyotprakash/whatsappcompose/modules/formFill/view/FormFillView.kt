/*
 * MIT License
 *
 * Copyright (c) 2021 Pradyot Prakash
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
*/
package com.project.pradyotprakash.whatsappcompose.modules.formFill.view

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.pradyotprakash.whatsappcompose.R
import com.project.pradyotprakash.whatsappcompose.modules.formFill.viewModel.FormFillViewModel
import com.project.pradyotprakash.whatsappcompose.ui.composables.CircularIndicatorMessage
import com.project.pradyotprakash.whatsappcompose.ui.composables.SizedBox
import com.project.pradyotprakash.whatsappcompose.ui.composables.Snackbar
import com.project.pradyotprakash.whatsappcompose.ui.theme.WhatsAppComposeTheme
import com.project.pradyotprakash.whatsappcompose.ui.theme.black20Bold
import com.project.pradyotprakash.whatsappcompose.ui.theme.gray12Italic
import com.project.pradyotprakash.whatsappcompose.ui.theme.gray15
import com.project.pradyotprakash.whatsappcompose.ui.theme.gray20Bold
import com.project.pradyotprakash.whatsappcompose.ui.theme.white20Bold
import com.project.pradyotprakash.whatsappcompose.utils.Utility
import kotlinx.coroutines.launch

/**
 * A form fill view which allows user to get the details from the user after signup.
 *
 * And update the details in the firestore for future user.
 */

@Composable
fun FormFillView(home: () -> Unit, formFillViewModel: FormFillViewModel = viewModel()) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = SnackbarHostState()

    val loading: Boolean by formFillViewModel.loading.observeAsState(initial = false)
    val showMessage: Boolean by formFillViewModel.showMessage.observeAsState(initial = false)
    val message: String by formFillViewModel.message.observeAsState(initial = "")
    val name: String by formFillViewModel.name.observeAsState(initial = "")
    val username: String by formFillViewModel.username.observeAsState(initial = "")
    val emailId: String by formFillViewModel.emailID.observeAsState(initial = "")
    val status: String by formFillViewModel.status.observeAsState(initial = "")


    /**
     * Show snackbar
     */
    val showSnackbar = {
        coroutineScope.launch {
            when (snackbarHostState.showSnackbar(
                message = message,
            )) {
                SnackbarResult.Dismissed -> {
                    formFillViewModel.snackbarDismissed()
                }
                SnackbarResult.ActionPerformed -> {
                    formFillViewModel.snackbarDismissed()
                }
            }
        }
    }

    if (showMessage) {
        Utility.showMessage(message = message)
        showSnackbar()
    }

    WhatsAppComposeTheme {
        Surface(color = MaterialTheme.colors.background) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 15.dp, vertical = 30.dp)
                    ) {
                        item {
                            Text(
                                stringResource(id = R.string.account_created),
                                style = black20Bold
                            )
                            Text(
                                stringResource(id = R.string.account_created_helper),
                                style = gray15
                            )
                            SizedBox(
                                height = 50
                            )
                            OutlinedTextField(
                                value = name,
                                textStyle = black20Bold,
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true,
                                onValueChange = { formFillViewModel.onNameChange(it) },
                                placeholder = {
                                    Text(
                                        stringResource(id = R.string.name_helper),
                                        style = gray20Bold
                                    )
                                },
                                label = {
                                    Text(
                                        stringResource(id = R.string.name),
                                        style = gray15
                                    )
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    unfocusedIndicatorColor = Color.LightGray,
                                    backgroundColor = Color.White
                                ),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Done,
                                    capitalization = KeyboardCapitalization.Words
                                ),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        focusManager.clearFocus()
                                    }
                                ),
                            )
                            SizedBox(height = 15)
                            OutlinedTextField(
                                value = username,
                                textStyle = black20Bold,
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true,
                                onValueChange = { formFillViewModel.onUsernameChange(it) },
                                placeholder = {
                                    Text(
                                        stringResource(id = R.string.username_helper),
                                        style = gray20Bold
                                    )
                                },
                                label = {
                                    Text(
                                        stringResource(id = R.string.username),
                                        style = gray15
                                    )
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    unfocusedIndicatorColor = Color.LightGray,
                                    backgroundColor = Color.White
                                ),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Done
                                ),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        focusManager.clearFocus()
                                    }
                                )
                            )
                            SizedBox(height = 15)
                            OutlinedTextField(
                                value = emailId,
                                textStyle = black20Bold,
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true,
                                onValueChange = { formFillViewModel.onEmailIdChange(it) },
                                placeholder = {
                                    Text(
                                        stringResource(id = R.string.email_id_helper),
                                        style = gray20Bold
                                    )
                                },
                                label = {
                                    Text(
                                        stringResource(id = R.string.emailId),
                                        style = gray15
                                    )
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    unfocusedIndicatorColor = Color.LightGray,
                                    backgroundColor = Color.White
                                ),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Email,
                                    imeAction = ImeAction.Done
                                ),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        focusManager.clearFocus()
                                    }
                                )
                            )
                            SizedBox(height = 5)
                            Text(
                                text = stringResource(id = R.string.email_id_note),
                                style = gray12Italic
                            )
                            SizedBox(height = 15)
                            OutlinedTextField(
                                value = status,
                                textStyle = black20Bold,
                                modifier = Modifier.fillMaxWidth(),
                                onValueChange = { formFillViewModel.onStatusChange(it) },
                                placeholder = {
                                    Text(
                                        stringResource(id = R.string.status_helper),
                                        style = gray20Bold
                                    )
                                },
                                label = {
                                    Text(
                                        stringResource(id = R.string.status),
                                        style = gray15
                                    )
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    unfocusedIndicatorColor = Color.LightGray,
                                    backgroundColor = Color.White
                                ),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Done,
                                    capitalization = KeyboardCapitalization.Sentences
                                ),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        focusManager.clearFocus()
                                        if (context is Activity) {
                                            formFillViewModel.updateUserDetails(context, home)
                                        }
                                    }
                                )
                            )
                            SizedBox(height = 50)
                            Button(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(50),
                                onClick = {
                                    focusManager.clearFocus()
                                    if (context is Activity) {
                                        formFillViewModel.updateUserDetails(context, home)
                                    }
                                }
                            ) {
                                Text(
                                    stringResource(id = R.string.continue_button).uppercase(),
                                    style = white20Bold,
                                    modifier = Modifier.padding(10.dp)
                                )
                            }
                        }
                    }
                    if (loading) {
                        CircularIndicatorMessage(
                            message = stringResource(id = R.string.please_wait)
                        )
                    }
                }
                Snackbar(snackbarHostState)
            }
        }
    }
}