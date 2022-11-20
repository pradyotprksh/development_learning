package com.project.pradyotprakash.rental.app.pages.wishlist.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.pradyotprakash.rental.app.composables.PageStateComposable
import com.project.pradyotprakash.rental.app.composables.PropertyDetailsComposable
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.app.pages.wishlist.viewmodel.WishlistViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishlistScreen(
    wishlistViewModel: WishlistViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = wishlistViewModel) {
        wishlistViewModel.getAllWishlist()
    }

    val loading = wishlistViewModel.loading.observeAsState(false)
    val error = wishlistViewModel.error.observeAsState("")
    val wishlists = wishlistViewModel.wishlists.observeAsState()

    PageStateComposable(
        isLoading = loading.value,
        errorMessage = error.value,
        dismissErrorAlert = wishlistViewModel::updateErrorState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.smallTopAppBarColors(),
                    title = { Text(text = TR.yourWatchlist) },
                    navigationIcon = {
                        IconButton(onClick = wishlistViewModel::navigateBack) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = Icons.Default.ArrowBack.name,
                            )
                        }
                    },
                )
            },
        ) { paddingValues ->
            wishlists.value?.let { allWishlist ->
                LazyColumn(
                    contentPadding = PaddingValues(
                        top = paddingValues.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding(),
                    )
                ) {
                    items(allWishlist) { wishlist ->
                        val propertyDetails = wishlist.property_details
                        Column {
                            propertyDetails?.let {
                                PropertyDetailsComposable(
                                    property = propertyDetails
                                ) { wishlistViewModel.navigateToPropertyDetails(it) }
                            }
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth().padding(
                                    horizontal = 10.dp,
                                )
                            ) {
                                Text(
                                    text = String.format(TR.added, wishlist.wishlistCreateOnTimeAgo)
                                )
                                IconButton(onClick = { TODO() }) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = Icons.Default.Delete.name
                                    )
                                }
                            }
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}