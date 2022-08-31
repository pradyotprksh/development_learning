package com.project.pradyotprakash.rental.app.pages.information.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.project.pradyotprakash.rental.app.composables.PageStateComposable
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.app.pages.information.viewmodel.InformationViewModel

/**
 * A screen for showing all the details related to the user,
 * if needed edit the details as well.
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun InformationScreen(
    informationViewModel: InformationViewModel
) {
    val fields = informationViewModel.fields.observeAsState(emptyList())
    val loading = informationViewModel.loading.observeAsState(false)
    val error = informationViewModel.error.observeAsState("")

    PageStateComposable(
        isLoading = loading.value,
        errorMessage = error.value,
        dismissErrorAlert = informationViewModel::updateErrorState
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            stickyHeader {
                SmallTopAppBar(
                    title = {
                        Text(
                            text = TR.informationMessage,
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center,
                        )
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(),
                    navigationIcon = {
                        if (informationViewModel.allowBackOption) {
                            IconButton(onClick = informationViewModel::navigateBack) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = Icons.Default.Phone.name,
                                )
                            }
                        }
                    },
                )

                Spacer(modifier = Modifier.height(15.dp))
            }

            items(fields.value.size) { index ->
                val field = fields.value[index]
                val filedValue = field.value.observeAsState("")

                OutlinedTextField(
                    value = filedValue.value,
                    onValueChange = {
                        if (field.maxChar == -1) {
                            informationViewModel.updateFieldState(index, it)
                        } else {
                            if (it.length <= field.maxChar) {
                                informationViewModel.updateFieldState(index, it)
                            }
                        }
                    },
                    label = { Text(field.label) },
                    readOnly = field.readOnly,
                    keyboardOptions = field.keyboardOptions,
                    visualTransformation = field.visualTransformation,
                    keyboardActions = field.keyboardActions,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
                        informationViewModel.updateUserDetails()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                ) {
                    Text(text = TR.save)
                }
            }
        }
    }
}