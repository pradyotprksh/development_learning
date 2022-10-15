package com.project.pradyotprakash.rental.app.pages.home.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.pradyotprakash.rental.R
import com.project.pradyotprakash.rental.app.composables.NetworkImageComposable
import com.project.pradyotprakash.rental.app.composables.PageStateComposable
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.app.pages.home.viewmodel.HomeViewModel
import com.project.pradyotprakash.rental.app.utils.UserType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    val loading = homeViewModel.loading.observeAsState(false)
    val error = homeViewModel.error.observeAsState("")
    val userDetails = homeViewModel.userDetails.observeAsState().value
    val properties = homeViewModel.properties.observeAsState()

    PageStateComposable(
        isLoading = loading.value,
        errorMessage = error.value,
        dismissErrorAlert = homeViewModel::updateErrorState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            NetworkImageComposable(
                                imageUrl = userDetails?.profile_pic_url ?: "",
                                size = 30.dp,
                                cornerSize = 15.dp,
                                error = R.drawable.user_image,
                                modifier = Modifier.clickable { TODO() }
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = String.format(
                                    TR.welcomeWithComma,
                                    userDetails?.first_name ?: ""
                                ),
                                style = MaterialTheme.typography.titleMedium,
                            )
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(),
                    actions = {
                        IconButton(onClick = { TODO() }) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = Icons.Default.Refresh.name,
                            )
                        }
                    }
                )
            },
            floatingActionButton = {
                userDetails?.let {
                    FloatingActionButton(
                        onClick = {
                            if (userDetails.userType == UserType.Owner) {
                                homeViewModel.goToAddPropertyScreen()
                            } else {
                                TODO()
                            }
                        }
                    ) {
                        Text(
                            text = if (userDetails.userType == UserType.Owner) TR.addAProperty else TR.searchForProperty,
                            modifier = Modifier.padding(
                                all = 15.dp
                            )
                        )
                    }
                }
            }
        ) { paddingValues ->
            properties.value?.let { propertiesList ->
                val propertySize = propertiesList.size
                if (propertySize == 0) {
                    Box(
                        modifier = Modifier
                            .padding(paddingValues)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = TR.noPropertiesAddedByYou,
                            textAlign = TextAlign.Center,
                        )
                    }
                } else {
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        contentPadding = PaddingValues(
                            top = paddingValues.calculateTopPadding(),
                            bottom = paddingValues.calculateBottomPadding(),
                        )
                    ) {
                        items(propertiesList) { property ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp)
                                    .clickable {
                                        homeViewModel.navigateToPropertyDetails(property.property_id)
                                    }
                            ) {
                                Column(
                                    modifier = Modifier.padding(10.dp)
                                ) {
                                    Row {
                                        Text(
                                            text = property.property_name,
                                            style = MaterialTheme.typography.titleMedium,
                                        )
                                        Spacer(modifier = Modifier.weight(1f))
                                        Text(
                                            text = TR.isForRental,
                                            color = if (property.isForRental) Color.Green else Color.Red
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(text = String.format(TR.addressColon, property.address))
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(
                                        text = String.format(
                                            TR.propertyForColon,
                                            property.propertyFor
                                        )
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(text = property.furnishedType)
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(
                                        text = String.format(
                                            TR.bathrooms,
                                            "${property.property_type} & ${property.number_of_bathrooms}"
                                        )
                                    )
                                    property.property_images?.let { images ->
                                        if (images.isNotEmpty()) {
                                            Spacer(modifier = Modifier.height(10.dp))
                                            LazyRow {
                                                items(images) { images ->
                                                    NetworkImageComposable(
                                                        imageUrl = images,
                                                        size = 50.dp,
                                                        cornerSize = 5.dp
                                                    )
                                                    Spacer(modifier = Modifier.width(5.dp))
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        // Bottom padding
                        item {
                            Spacer(modifier = Modifier.height(paddingValues.calculateBottomPadding() + 100.dp))
                        }
                    }
                }
            }
        }
    }
}