package com.project.pradyotprakash.rental.app.pages.property.details.view.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.pradyotprakash.rental.app.composables.CardSwitchComposable
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.domain.modal.PropertyEntity

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ProposalFormComposable(
    propertyEntity: PropertyEntity,
    closeAction: () -> Unit = {},
) {
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
                onClick = { TODO() },
                modifier = Modifier
                    .fillMaxWidth().padding(15.dp),
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

            item {
                Column {
                    CardSwitchComposable(
                        text = String.format(TR.okayWithRent, propertyEntity.monthly_rent),
                        value = false,
                        onChange = {}
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    CardSwitchComposable(
                        text = String.format(TR.okayWithDeposit, propertyEntity.yearly_deposit),
                        value = false,
                        onChange = {}
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                    )
                }
            }
        }
    }
}