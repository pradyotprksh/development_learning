package com.project.pradyotprakash.rental.app.pages.information.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.project.pradyotprakash.rental.app.composables.PageStateComposable
import com.project.pradyotprakash.rental.app.pages.information.viewmodel.InformationViewModel

/**
 * A screen for showing all the details related to the user,
 * if needed edit the details as well.
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun InformationScreen(
    informationViewModel: InformationViewModel
) {
    val fields = informationViewModel.fields.observeAsState(emptyList())

    PageStateComposable {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            stickyHeader {
                SmallTopAppBar(
                    title = {
                        Text(
                            text = "Please enter the below informations.",
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center,
                        )
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(),
                    navigationIcon = {
                        IconButton(onClick = informationViewModel::navigateBack) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = Icons.Default.Phone.name,
                            )
                        }
                    },
                )

                Spacer(modifier = Modifier.height(15.dp))
            }

            items(fields.value.size) { index ->
                val field = fields.value[index]

                OutlinedTextField(
                    value = field.value,
                    onValueChange = { informationViewModel.updateFieldState(index, it) },
                    label = { Text(field.label) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}