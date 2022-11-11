package com.project.pradyotprakash.rental.app.pages.search.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.pradyotprakash.rental.app.composables.AppBarTextField
import com.project.pradyotprakash.rental.app.composables.HeaderComposable
import com.project.pradyotprakash.rental.app.composables.PageStateComposable
import com.project.pradyotprakash.rental.app.composables.PropertyDetailsComposable
import com.project.pradyotprakash.rental.app.composables.UserDetailsComposable
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.app.pages.search.viewmodel.SearchViewModel
import com.project.pradyotprakash.rental.app.utils.UserType

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SearchView(
    searchViewModel: SearchViewModel = hiltViewModel(),
    allowSearch: Boolean,
    userType: UserType,
) {
    LaunchedEffect(allowSearch, userType) {
        searchViewModel.startSearch(allowSearch, userType)
    }

    val loading = searchViewModel.loading.observeAsState(false)
    val error = searchViewModel.error.observeAsState("")
    val searchText = searchViewModel.searchText.observeAsState("")
    val searchResult = searchViewModel.searchResult.observeAsState()

    PageStateComposable(
        isLoading = loading.value,
        errorMessage = error.value,
        dismissErrorAlert = searchViewModel::updateErrorState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.smallTopAppBarColors(),
                    title = {
                        if (allowSearch) {
                            AppBarTextField(
                                value = searchText.value,
                                onValueChange = searchViewModel::updateSearchText
                            )
                        } else {
                            Text(text = TR.search)
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = searchViewModel::navigateBack) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = Icons.Default.ArrowBack.name,
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
                )
            ) {
                searchResult.value?.let { result ->
                    val properties = result.properties
                    val users = result.users

                    if (userType == UserType.Unknown) {
                        stickyHeader {
                            HeaderComposable(
                                title = String.format(
                                    TR.searchResultProperties,
                                    properties.count
                                )
                            )
                        }

                        properties.details.forEach {
                            item {
                                PropertyDetailsComposable(
                                    property = it,
                                    onClick = searchViewModel::navigateToPropertyDetails
                                )
                            }
                        }

                        stickyHeader {
                            HeaderComposable(
                                title = String.format(
                                    TR.searchResultUsers,
                                    users.count
                                )
                            )
                        }
                    }

                    val owners = users.details.filter { it.userType == UserType.Owner }
                    val renters = users.details.filter { it.userType == UserType.Renter }

                    val showOwners = userType != UserType.Renter
                    val showRenters = userType != UserType.Owner

                    if (showOwners) {
                        stickyHeader {
                            Row {
                                Spacer(modifier = Modifier.width(5.dp))
                                HeaderComposable(
                                    title = String.format(
                                        TR.searchResultOwners,
                                        owners.count()
                                    )
                                )
                            }
                        }
                        owners.forEach {
                            item {
                                UserDetailsComposable(
                                    userDetails = it,
                                    onClick = searchViewModel::goToUserDetails
                                )
                            }
                        }
                    }

                    if (showRenters) {
                        stickyHeader {
                            Row {
                                Spacer(modifier = Modifier.width(5.dp))
                                HeaderComposable(
                                    title = String.format(
                                        TR.searchResultRenters,
                                        renters.count()
                                    )
                                )
                            }
                        }
                        renters.forEach {
                            item {
                                UserDetailsComposable(
                                    userDetails = it,
                                    onClick = searchViewModel::goToUserDetails
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}