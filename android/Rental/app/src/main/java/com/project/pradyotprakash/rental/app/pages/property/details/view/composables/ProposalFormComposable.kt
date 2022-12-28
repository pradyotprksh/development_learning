package com.project.pradyotprakash.rental.app.pages.property.details.view.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.pradyotprakash.rental.app.composables.CardSwitchComposable
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.app.pages.property.details.viewmodel.PropertyDetailsViewModel
import com.project.pradyotprakash.rental.app.utils.ProposalStatus
import com.project.pradyotprakash.rental.domain.modal.PropertyEntity

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ProposalFormComposable(
    propertyDetailsViewModel: PropertyDetailsViewModel = hiltViewModel(),
    propertyEntity: PropertyEntity,
    closeAction: () -> Unit = {},
) {
    val okayWithRent = propertyDetailsViewModel.okayWithRent.observeAsState(true)
    val okayWithDeposit = propertyDetailsViewModel.okayWithDeposit.observeAsState(true)
    val proposalRent = propertyDetailsViewModel.proposalRent.observeAsState("")
    val proposalDeposit = propertyDetailsViewModel.proposalDeposit.observeAsState("")

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(),
                title = {
                    Text(text = TR.sendProposal)
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
        bottomBar = {
            Button(
                onClick = {
                    propertyDetailsViewModel.validateProposal(
                        propertyEntity.property_id,
                        closeAction
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
            ) {
                Text(text = TR.sendProposal)
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(
                bottom = padding.calculateBottomPadding(),
                top = padding.calculateTopPadding(),
                start = 5.dp,
                end = 5.dp,
            )
        ) {
            stickyHeader {
                PropertyDetailsComposable(property = propertyEntity)
            }

            propertyEntity.proposal_details?.let {
                stickyHeader {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 10.dp,
                            )
                    ) {
                        Text(
                            text = String.format(TR.proposalUpdatedOn, it.proposalUpdatedOnTimeAgo)
                        )
                        IconButton(
                            onClick = {
                                propertyDetailsViewModel.confirmDeleteProposal(
                                    propertyId = propertyEntity.property_id,
                                    propertyName = propertyEntity.property_name,
                                    closeAction = closeAction,
                                )
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = Icons.Default.Delete.name
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            propertyEntity.proposal_details?.proposalStatus?.let { status ->
                if (status != ProposalStatus.None) {
                    stickyHeader {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (status == ProposalStatus.Accept) Color.Green else Color.Red,
                            )
                        ) {
                            Text(
                                text = if (status == ProposalStatus.Accept) TR.proposalAccepted else TR.proposalRejected,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(all = 10.dp),
                                color = Color.Black,
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }

            item {
                Column {
                    CardSwitchComposable(
                        text = String.format(TR.okayWithRent, propertyEntity.monthly_rent),
                        value = okayWithRent.value,
                        onChange = propertyDetailsViewModel::okayWithRent
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    if (!okayWithRent.value) {
                        OutlinedTextField(
                            value = proposalRent.value,
                            onValueChange = propertyDetailsViewModel::proposalRent,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                            label = { Text(TR.pleaseEnterProposalRent) },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done,
                            ),
                        )

                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    CardSwitchComposable(
                        text = String.format(TR.okayWithDeposit, propertyEntity.yearly_deposit),
                        value = okayWithDeposit.value,
                        onChange = propertyDetailsViewModel::okayWithDeposit
                    )

                    if (!okayWithDeposit.value) {
                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            value = proposalDeposit.value,
                            onValueChange = propertyDetailsViewModel::proposalDeposit,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                            label = { Text(TR.pleaseEnterProposalDeposit) },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done,
                            )
                        )
                    }
                }
            }
        }
    }
}