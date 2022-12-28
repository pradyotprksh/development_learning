package com.project.pradyotprakash.rental.app.pages.property.details.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.pradyotprakash.rental.app.composables.ConfirmationDialog
import com.project.pradyotprakash.rental.app.composables.HeaderComposable
import com.project.pradyotprakash.rental.app.composables.NetworkImageComposable
import com.project.pradyotprakash.rental.app.composables.PageStateComposable
import com.project.pradyotprakash.rental.app.composables.UserDetailsComposable
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.app.pages.property.details.PropertyPageState
import com.project.pradyotprakash.rental.app.pages.property.details.view.composables.MoneyDetailsComposable
import com.project.pradyotprakash.rental.app.pages.property.details.view.composables.NoPropertyDetailsComposable
import com.project.pradyotprakash.rental.app.pages.property.details.view.composables.OtherDetailsComposable
import com.project.pradyotprakash.rental.app.pages.property.details.view.composables.PropertyDetailsComposable
import com.project.pradyotprakash.rental.app.pages.property.details.view.composables.ProposalFormComposable
import com.project.pradyotprakash.rental.app.pages.property.details.view.composables.ProposalsComposable
import com.project.pradyotprakash.rental.app.pages.property.details.view.composables.RentDetailsComposable
import com.project.pradyotprakash.rental.app.pages.property.details.viewmodel.PropertyDetailsViewModel
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalMaterialApi::class
)
@Composable
fun PropertyDetailsScreen(
    propertyId: String,
    propertyDetailsViewModel: PropertyDetailsViewModel = hiltViewModel(),
) {
    LaunchedEffect(propertyId) {
        propertyDetailsViewModel.getPropertyDetails(propertyId)
    }

    var pageState by remember { mutableStateOf<PropertyPageState>(PropertyPageState.Normal) }
    val loading = propertyDetailsViewModel.loading.observeAsState(false)
    val error = propertyDetailsViewModel.error.observeAsState("")
    val propertyDetails = propertyDetailsViewModel.propertyDetails.observeAsState(null)
    val noProperties = propertyDetailsViewModel.noProperties.observeAsState(false)
    val confirmationDialog = propertyDetailsViewModel.confirmationDialog.observeAsState(
        ConfirmationDialog()
    )
    val isPropertyOwner =
        propertyDetailsViewModel.isPropertyOwner(propertyDetails.value?.property_created_by)

    val scaffoldState = rememberBackdropScaffoldState(
        BackdropValue.Revealed
    )
    val scope = rememberCoroutineScope()

    val openSendProposal: (PropertyPageState) -> Unit = { state ->
        pageState = state
        scope.launch { scaffoldState.conceal() }
    }
    val closeSheet = {
        scope.launch {
            pageState = PropertyPageState.Normal
            scaffoldState.reveal()
        }
    }

    PageStateComposable(
        isLoading = loading.value,
        errorMessage = error.value,
        dismissErrorAlert = propertyDetailsViewModel::updateErrorState,
        confirmationDialog = confirmationDialog.value,
    ) {
        BackdropScaffold(
            scaffoldState = scaffoldState,
            peekHeight = 0.dp,
            headerHeight = 0.dp,
            backLayerBackgroundColor = MaterialTheme.colorScheme.background,
            frontLayerBackgroundColor = MaterialTheme.colorScheme.background,
            appBar = {
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
                    actions = {
                        propertyDetails.value?.proposals?.let {
                            if (it.isNotEmpty()) {
                                TextButton(
                                    onClick = {
                                        openSendProposal(PropertyPageState.Proposals)
                                    },
                                ) {
                                    Text(TR.proposals)
                                }
                            }
                        }
                    }
                )
            },
            backLayerContent = {
                Scaffold(
                    floatingActionButton = {
                        if (!loading.value) {
                            Row {
                                FloatingActionButton(
                                    onClick = {
                                        if (isPropertyOwner) {
                                            propertyDetailsViewModel.searchForRenter()
                                        } else {
                                            openSendProposal(PropertyPageState.CreateProposal)
                                        }
                                    }
                                ) {
                                    Text(
                                        text = if (isPropertyOwner) TR.searchForRenter else TR.sendProposalToOwner,
                                        modifier = Modifier.padding(
                                            all = 15.dp
                                        )
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                if (propertyDetailsViewModel.showWishListOption()) {
                                    FloatingActionButton(
                                        onClick = {
                                            if (isPropertyOwner) {
                                                propertyDetailsViewModel.goToPropertyEdit(propertyId)
                                            } else {
                                                propertyDetailsViewModel.confirmAddToWishList(
                                                    propertyId,
                                                    propertyDetails.value?.property_name ?: ""
                                                )
                                            }
                                        }
                                    ) {
                                        Text(
                                            text = if (isPropertyOwner) TR.edit else TR.addToWishList,
                                            modifier = Modifier.padding(
                                                all = 15.dp
                                            )
                                        )
                                    }
                                }
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
                                            HeaderComposable(
                                                title =
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
            },
            frontLayerContent = {
                propertyDetails.value?.let {
                    when (pageState) {
                        PropertyPageState.CreateProposal -> {
                            ProposalFormComposable(
                                propertyEntity = it
                            ) {
                                closeSheet()
                            }
                        }
                        PropertyPageState.Proposals -> {
                            ProposalsComposable(propertyEntity = it) {
                                closeSheet()
                            }
                        }
                        PropertyPageState.Normal -> {}
                    }
                }
            }
        )
    }
}