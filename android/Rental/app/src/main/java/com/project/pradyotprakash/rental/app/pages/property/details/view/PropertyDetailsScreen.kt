package com.project.pradyotprakash.rental.app.pages.property.details.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.project.pradyotprakash.rental.app.composables.HeaderComposable
import com.project.pradyotprakash.rental.app.composables.NetworkImageComposable
import com.project.pradyotprakash.rental.app.composables.PageStateComposable
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.app.pages.property.details.view.composables.MoneyDetailsComposable
import com.project.pradyotprakash.rental.app.pages.property.details.view.composables.NoPropertyDetailsComposable
import com.project.pradyotprakash.rental.app.pages.property.details.view.composables.OtherDetailsComposable
import com.project.pradyotprakash.rental.app.pages.property.details.view.composables.PropertyDetailsComposable
import com.project.pradyotprakash.rental.app.pages.property.details.view.composables.RentDetailsComposable
import com.project.pradyotprakash.rental.app.pages.property.details.view.composables.UserDetailsComposable
import com.project.pradyotprakash.rental.app.pages.property.details.viewmodel.PropertyDetailsViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun PropertyDetailsScreen(
    propertyId: String,
    propertyDetailsViewModel: PropertyDetailsViewModel,
) {
    LaunchedEffect(Unit) {
        propertyDetailsViewModel.getPropertyDetails(propertyId)
    }

    val loading = propertyDetailsViewModel.loading.observeAsState(false)
    val error = propertyDetailsViewModel.error.observeAsState("")
    val propertyDetails = propertyDetailsViewModel.propertyDetails.observeAsState(null)
    val noProperties = propertyDetailsViewModel.noProperties.observeAsState(false)

    PageStateComposable(
        isLoading = loading.value,
        errorMessage = error.value,
        dismissErrorAlert = propertyDetailsViewModel::updateErrorState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = propertyDetails.value?.property_name ?: TR.loading,
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center,
                        )
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(),
                    navigationIcon = {
                        IconButton(onClick = propertyDetailsViewModel::navigateBack) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = Icons.Default.ArrowBack.name,
                            )
                        }
                    },
                )
            },
            floatingActionButton = {
                if (!loading.value) {
                    FloatingActionButton(onClick = {}) {
                        Text(
                            text = if (propertyDetailsViewModel.isPropertyOwner(propertyDetails.value?.property_created_by)) TR.searchForRenter else TR.sendProposalToOwner,
                            modifier = Modifier.padding(
                                all = 15.dp
                            )
                        )
                    }
                }
            },
            content = { padding ->
                LazyColumn(
                    modifier = Modifier.padding(
                        bottom = padding.calculateBottomPadding(),
                        top = padding.calculateTopPadding(),
                    )
                ) {
                    if (noProperties.value) {
                        item {
                            NoPropertyDetailsComposable()
                        }
                    } else {
                        propertyDetails.value?.let { property ->
                            stickyHeader {
                                HeaderComposable(title = TR.basicDetails)
                            }
                            item {
                                PropertyDetailsComposable(property = property)
                            }

                            property.property_created_by_details?.let { userDetails ->
                                stickyHeader {
                                    HeaderComposable(title =
                                        if (property.isRentalOwner)
                                            TR.ownerDetails
                                        else
                                            TR.propertyListerDetails
                                    )
                                }
                                item {
                                    UserDetailsComposable(userDetails = userDetails)
                                }
                            }

                            stickyHeader {
                                HeaderComposable(title = TR.rentDetails)
                            }
                            item {
                                RentDetailsComposable(property = property)
                            }

                            stickyHeader {
                                HeaderComposable(title = TR.moneyDetails)
                            }
                            item {
                                MoneyDetailsComposable(property = property)
                            }

                            property.property_images?.let { images ->
                                if (images.isNotEmpty()) {
                                    stickyHeader {
                                        HeaderComposable(title = TR.images)
                                    }
                                    item {
                                        LazyRow(
                                            modifier = Modifier.padding(15.dp)
                                        ) {
                                            items(images) { photo ->
                                                NetworkImageComposable(
                                                    imageUrl = photo,
                                                    size = 150.dp,
                                                    cornerSize = 5.dp,
                                                )
                                                Spacer(modifier = Modifier.width(10.dp))
                                            }
                                        }
                                    }
                                }
                            }

                            if (property.perks.isNotEmpty() || property.agreement_rules.isNotEmpty()) {
                                stickyHeader {
                                    HeaderComposable(title = TR.otherDetails)
                                }

                                item {
                                    OtherDetailsComposable(
                                        perks = property.perks,
                                        agreement = property.agreement_rules
                                    )
                                }
                            }
                        }
                    }

                    // Bottom padding
                    item {
                        Spacer(modifier = Modifier.height(padding.calculateBottomPadding() + 150.dp))
                    }
                }
            }
        )
    }
}