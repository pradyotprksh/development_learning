package com.project.pradyotprakash.rental.app.composables.location

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.pradyotprakash.rental.app.composables.NetworkImageComposable
import com.project.pradyotprakash.rental.app.composables.PageStateComposable
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.core.models.FieldStates

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationPicker(
    locationPickerViewModel: LocationPickerViewModel = hiltViewModel(),
    field: FieldStates,
    closeAction: () -> Unit = {},
) {
    var searchText by remember { mutableStateOf("") }
    var zipCode by remember { mutableStateOf("") }
    var latitude by remember { mutableStateOf("") }
    var longitude by remember { mutableStateOf("") }
    val loading = locationPickerViewModel.loading.observeAsState(false)
    val error = locationPickerViewModel.error.observeAsState("")
    val locationResult = locationPickerViewModel.locationResult.observeAsState(emptyList())

    val startSearch = {
        locationPickerViewModel.searchForLocation(
            searchText,
            zipCode,
            latitude,
            longitude
        )
    }

    PageStateComposable(
        isLoading = loading.value,
        errorMessage = error.value,
        dismissErrorAlert = locationPickerViewModel::updateErrorState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.smallTopAppBarColors(),
                    title = {},
                    navigationIcon = {
                        IconButton(onClick = closeAction) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = Icons.Default.Close.name,
                            )
                        }
                    },
                )
            },
        ) { padding ->
            LazyColumn(
                modifier = Modifier.padding(
                    bottom = padding.calculateBottomPadding(),
                    top = padding.calculateTopPadding(),
                    start = 15.dp,
                    end = 15.dp,
                )
            ) {
                item {
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            OutlinedTextField(
                                value = searchText,
                                onValueChange = { searchText = it },
                                label = { Text(TR.textSearch) },
                                keyboardOptions = KeyboardOptions(
                                    capitalization = KeyboardCapitalization.Words,
                                    autoCorrect = false,
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Done,
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                keyboardActions = KeyboardActions(
                                    onDone = { startSearch() }
                                )
                            )
                            Text(
                                text = TR.or,
                                modifier = Modifier.padding(5.dp)
                            )
                            OutlinedTextField(
                                value = zipCode,
                                onValueChange = { zipCode = it },
                                label = { Text(TR.zipCodeSearch) },
                                keyboardOptions = KeyboardOptions(
                                    autoCorrect = false,
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Done,
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                keyboardActions = KeyboardActions(
                                    onDone = { startSearch() }
                                )
                            )
                        }
                        Text(
                            text = TR.or,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            OutlinedTextField(
                                value = "",
                                onValueChange = { latitude = it },
                                label = { Text(TR.latitude) },
                                keyboardOptions = KeyboardOptions(
                                    autoCorrect = false,
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Next,
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            OutlinedTextField(
                                value = "",
                                onValueChange = { longitude = it },
                                label = { Text(TR.longitude) },
                                keyboardOptions = KeyboardOptions(
                                    autoCorrect = false,
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Done,
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                keyboardActions = KeyboardActions(
                                    onDone = { startSearch() }
                                )
                            )
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(10.dp))
                }

                items(locationResult.value) { location ->
                    Column {
                        Divider()
                        Spacer(modifier = Modifier.height(5.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.clickable {
                                field.locationDetails.value = location
                                closeAction()
                            }
                        ) {
                            NetworkImageComposable(
                                imageUrl = location.icon ?: "",
                                size = 20.dp,
                                cornerSize = 0.dp,
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = location.display_name)
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}