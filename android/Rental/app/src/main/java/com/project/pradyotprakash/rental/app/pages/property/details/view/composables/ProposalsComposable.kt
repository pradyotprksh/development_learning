package com.project.pradyotprakash.rental.app.pages.property.details.view.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.pradyotprakash.rental.app.composables.UserDetailsComposable
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.app.pages.property.details.viewmodel.PropertyDetailsViewModel
import com.project.pradyotprakash.rental.domain.modal.PropertyEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProposalsComposable(
    propertyDetailsViewModel: PropertyDetailsViewModel = hiltViewModel(),
    propertyEntity: PropertyEntity,
    closeAction: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(),
                title = {
                    Text(text = TR.proposals)
                },
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
                start = 5.dp,
                end = 5.dp,
            )
        ) {
            val proposals =
                propertyEntity.proposals?.sortedByDescending { it.created_on } ?: emptyList()
            items(proposals) { proposal ->
                Card(
                    modifier = Modifier.padding(all = 5.dp)
                ) {
                    proposal.proposals_created_by_details?.let { userDetails ->
                        UserDetailsComposable(userDetails = userDetails) {
                            // TODO: Navigation clear the screen when pop back
                            propertyDetailsViewModel.goToUserDetails(userDetails.user_id)
                        }
                    }
                    Column(
                        modifier = Modifier.padding(all = 10.dp)
                    ) {
                        Text(text = String.format(TR.createdOnColon, proposal.proposalCreateOnTimeAgo))
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = String.format(TR.updatedOnColon, proposal.proposalUpdatedOnTimeAgo))
                        Spacer(modifier = Modifier.height(15.dp))
                        Row {
                            Text(TR.rent)
                            Spacer(modifier = Modifier.weight(1f))
                            if (proposal.confirm_rent)
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = Icons.Default.Check.name
                                )
                            else
                                Text(proposal.rent_proposal)
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(TR.deposit)
                            Spacer(modifier = Modifier.weight(1f))
                            if (proposal.confirm_deposit)
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = Icons.Default.Check.name
                                )
                            else
                                Text(proposal.deposit_proposal)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(
                                onClick = {},
                            ) {
                                Text(TR.accept)
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            Button(
                                onClick = {},
                            ) {
                                Text(TR.reject)
                            }
                        }
                    }
                }
            }
        }
    }
}