package com.project.pradyotprakash.rental.app.pages.home.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.project.pradyotprakash.rental.R
import com.project.pradyotprakash.rental.app.composables.HeaderComposable
import com.project.pradyotprakash.rental.app.composables.NetworkImageComposable
import com.project.pradyotprakash.rental.app.composables.PageStateComposable
import com.project.pradyotprakash.rental.app.composables.PropertyDetailsComposable
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.app.pages.home.viewmodel.HomeViewModel
import com.project.pradyotprakash.rental.app.utils.UserType
import com.project.pradyotprakash.rental.core.permissions.PermissionHandler

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    val loading = homeViewModel.loading.observeAsState(false)
    val error = homeViewModel.error.observeAsState("")
    val userDetails = homeViewModel.userDetails.observeAsState().value
    val properties = homeViewModel.properties.observeAsState()
    val nearByProperties = homeViewModel.nearByProperties.observeAsState()
    val otherProperties = homeViewModel.otherProperties.observeAsState()
    val locationDetails = homeViewModel.locationResult.observeAsState().value?.firstOrNull()

    userDetails?.let {
        if (userDetails.userType == UserType.Renter) {
            val accuratePermission = PermissionHandler.checkForAccurateLocation()
            val approximatePermission = PermissionHandler.checkForApproximateLocation()

            PermissionHandler.permissionInitiatorWithStateCheck(
                state = accuratePermission,
                askForPermission = {
                    accuratePermission.launchPermissionRequest()
                },
                onDenied = {
                    PermissionHandler.permissionInitiatorWithStateCheck(
                        state = approximatePermission,
                        askForPermission = {
                            approximatePermission.launchPermissionRequest()
                        },
                    )
                },
            )
        }
    }

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
                                modifier = Modifier.clickable { homeViewModel.goToUserDetails() }
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = String.format(
                                        TR.welcomeWithComma,
                                        userDetails?.first_name ?: ""
                                    ),
                                    style = MaterialTheme.typography.titleMedium,
                                )
                                locationDetails?.let {
                                    Spacer(modifier = Modifier.height(5.dp))
                                    Text(
                                        text = locationDetails.display_name,
                                        overflow = TextOverflow.Ellipsis,
                                        maxLines = 1,
                                        style = MaterialTheme.typography.bodySmall,
                                    )
                                }
                            }
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(),
                    actions = {
                        IconButton(onClick = { homeViewModel.goToSearchPage() }) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = Icons.Default.Search.name,
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
                            PropertyDetailsComposable(property = property) {
                                homeViewModel.navigateToPropertyDetails(it)
                            }
                        }

                        // Bottom padding
                        item {
                            Spacer(modifier = Modifier.height(paddingValues.calculateBottomPadding() + 100.dp))
                        }
                    }
                }
            }

            nearByProperties.value?.let { nearByProperties ->
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    contentPadding = PaddingValues(
                        top = paddingValues.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding(),
                    )
                ) {
                    stickyHeader {
                        HeaderComposable(title = TR.nearbyProperties)
                    }

                    items(nearByProperties) { property ->
                        PropertyDetailsComposable(property = property) {
                            homeViewModel.navigateToPropertyDetails(it)
                        }
                    }

                    otherProperties.value?.ifEmpty {
                        // Bottom padding
                        item {
                            Spacer(modifier = Modifier.height(paddingValues.calculateBottomPadding() + 100.dp))
                        }
                    }
                }
            }

            otherProperties.value?.let { otherProperties ->
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    contentPadding = PaddingValues(
                        top = paddingValues.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding(),
                    )
                ) {
                    stickyHeader {
                        HeaderComposable(title = TR.otherProperties)
                    }

                    items(otherProperties) { property ->
                        PropertyDetailsComposable(property = property) {
                            homeViewModel.navigateToPropertyDetails(it)
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