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
package com.project.pradyotprakash.whatsappcompose.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.project.pradyotprakash.whatsappcompose.R
import com.project.pradyotprakash.whatsappcompose.ui.theme.actionBold
import com.project.pradyotprakash.whatsappcompose.ui.theme.black20Bold

/**
 * A snack bar composable which will be used to show message to a user.
 *
 * [snackbarHostState] will be used to manage the state of the snackbar.
 */

@Composable
fun Snackbar(snackbarHostState: SnackbarHostState) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (snackbarHostRef) = createRefs()

        SnackbarHost(
            modifier = Modifier
                .constrainAs(snackbarHostRef) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            hostState = snackbarHostState,
            snackbar = {
                Snackbar(
                    backgroundColor = Color.White,
                    action = {
                        Text(
                            text = stringResource(id = R.string.okay),
                            modifier = Modifier
                                .padding(16.dp)
                                .clickable {
                                    snackbarHostState.currentSnackbarData?.dismiss()
                                },
                            style = actionBold
                        )
                    },
                    modifier = Modifier.padding(15.dp)
                ) {
                    Text(
                        text =
                        if ((snackbarHostState.currentSnackbarData?.message ?: "").isEmpty())
                            stringResource(id = R.string.something_went_wrong)
                        else
                            snackbarHostState.currentSnackbarData!!.message,
                        style = black20Bold
                    )
                }
            },
        )
    }
}