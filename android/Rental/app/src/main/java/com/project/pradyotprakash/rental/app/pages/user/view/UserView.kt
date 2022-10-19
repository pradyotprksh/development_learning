package com.project.pradyotprakash.rental.app.pages.user.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import com.project.pradyotprakash.rental.app.composables.HeaderComposable
import com.project.pradyotprakash.rental.app.composables.PageStateComposable
import com.project.pradyotprakash.rental.app.composables.PropertyDetailsComposable
import com.project.pradyotprakash.rental.app.composables.UserDetailsComposable
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.app.pages.user.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun UserView(
    userViewModel: UserViewModel = hiltViewModel(),
    userId: String,
) {
    LaunchedEffect(key1 = userId) {
        userViewModel.getUserDetails(userId)
    }

    val loading = userViewModel.loading.observeAsState(false)
    val error = userViewModel.error.observeAsState("")
    val userDetails = userViewModel.userDetails.observeAsState()

    PageStateComposable(
        isLoading = loading.value,
        errorMessage = error.value,
        dismissErrorAlert = userViewModel::updateErrorState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.smallTopAppBarColors(),
                    title = {},
                    navigationIcon = {
                        IconButton(onClick = userViewModel::navigateBack) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = Icons.Default.ArrowBack.name,
                            )
                        }
                    },
                )
            },
            floatingActionButton = {
                val actionText = userViewModel.floatingActionButtonText()
                if (actionText.isNotEmpty()) {
                    FloatingActionButton(onClick = { userViewModel.goToInformationScreen() }) {
                        Text(
                            text = userViewModel.floatingActionButtonText(),
                            modifier = Modifier.padding(
                                all = 15.dp
                            )
                        )
                    }
                }
            },
            bottomBar = {
                if (userViewModel.isCurrentUser()) {
                    Button(
                        onClick = userViewModel::logoutUser,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp)
                    ) {
                        Text(text = TR.logout)
                    }
                }
            }
        ) { padding ->
            LazyColumn(
                modifier = Modifier.padding(
                    bottom = padding.calculateBottomPadding(),
                    top = padding.calculateTopPadding(),
                )
            ) {
                userDetails.value?.let { details ->
                    item {
                        UserDetailsComposable(userDetails = details)
                    }

                    details.properties?.let { properties ->
                        stickyHeader {
                            HeaderComposable(title = TR.properties)
                        }

                        properties.forEach { details ->
                            item {
                                PropertyDetailsComposable(property = details) {
                                    userViewModel.navigateToPropertyDetails(it)
                                }
                            }
                        }
                    }
                } ?: run {}
            }
        }
    }
}