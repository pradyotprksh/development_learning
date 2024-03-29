package com.project.pradyotprakash.rental.app.pages.filter.view.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.pradyotprakash.rental.app.composables.field.FieldComposable
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.app.pages.filter.utils.FilterOptions
import com.project.pradyotprakash.rental.app.pages.filter.viewmodel.FilterViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FiltersSheet(
    filterViewModel: FilterViewModel = hiltViewModel(),
    closeAction: () -> Unit = {},
) {
    val selectedOption = filterViewModel.optionSelected.observeAsState(FilterOptions.Text)
    val basicFields = filterViewModel.basicFields.observeAsState(emptyList())
    val textFields = filterViewModel.textFields.observeAsState(emptyList())
    val moneyFields = filterViewModel.moneyFields.observeAsState(emptyList())
    val timelineFields = filterViewModel.timelineFields.observeAsState(emptyList())
    val locationFields = filterViewModel.locationFields.observeAsState(emptyList())

    val configuration = LocalConfiguration.current
    val bottomSheetHeight = (configuration.screenHeightDp * 0.65).dp

    Box(
        modifier = Modifier
            .height(bottomSheetHeight)
            .fillMaxWidth()
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.smallTopAppBarColors(),
                    title = { Text(text = TR.chooseFilters) },
                    actions = {
                        Button(
                            onClick = {
                                filterViewModel.clearFilters()
                            }
                        ) {
                            Text(TR.clear)
                        }
                        Button(
                            onClick = closeAction
                        ) {
                            Text(TR.apply)
                        }
                    }
                )
            },
        ) { padding ->
            Row {
                Box(
                    Modifier
                        .weight(0.35f)
                        .fillMaxHeight()
                        .padding(
                            bottom = padding.calculateBottomPadding(),
                            top = padding.calculateTopPadding(),
                        )
                ) {
                    LazyColumn(
                        contentPadding = PaddingValues(horizontal = 10.dp)
                    ) {
                        items(FilterOptions.values()) { option ->
                            TextButton(
                                onClick = {
                                    filterViewModel.onOptionSelected(option)
                                },
                                modifier = Modifier.fillMaxWidth(),
                                border = if (option == selectedOption.value) BorderStroke(
                                    3.dp, MaterialTheme.colorScheme.onPrimary
                                ) else null
                            ) {
                                Text(option.name)
                            }
                        }
                    }
                }
                Box(
                    Modifier
                        .width(1.dp)
                        .fillMaxHeight()
                        .padding(
                            bottom = padding.calculateBottomPadding(),
                            top = padding.calculateTopPadding(),
                        )
                        .background(color = Color.LightGray)
                ) {}
                Box(
                    Modifier
                        .weight(0.65f)
                        .fillMaxHeight()
                        .padding(
                            bottom = padding.calculateBottomPadding(),
                            top = padding.calculateTopPadding(),
                        )
                ) {
                    when (selectedOption.value) {
                        FilterOptions.Text -> {
                            FieldComposable(
                                fields = textFields,
                                showAppBar = false,
                                onValueChange = { index, value ->
                                    filterViewModel.updateBasicFieldState(
                                        index,
                                        value,
                                        FilterOptions.Text
                                    )
                                },
                            )
                        }
                        FilterOptions.Basic -> {
                            FieldComposable(
                                fields = basicFields,
                                showAppBar = false,
                                onValueChange = { index, value ->
                                    filterViewModel.updateBasicFieldState(
                                        index,
                                        value,
                                        FilterOptions.Basic
                                    )
                                },
                            )
                        }
                        FilterOptions.Money -> {
                            FieldComposable(
                                fields = moneyFields,
                                showAppBar = false,
                                onValueChange = { index, value ->
                                    filterViewModel.updateBasicFieldState(
                                        index,
                                        value,
                                        FilterOptions.Money
                                    )
                                },
                            )
                        }
                        FilterOptions.Timeline -> {
                            FieldComposable(
                                fields = timelineFields,
                                showAppBar = false,
                                onValueChange = { index, value ->
                                    filterViewModel.updateBasicFieldState(
                                        index,
                                        value,
                                        FilterOptions.Timeline
                                    )
                                },
                            )
                        }
                        FilterOptions.Location -> {
                            FieldComposable(
                                fields = locationFields,
                                showAppBar = false,
                                onValueChange = { index, value ->
                                    filterViewModel.updateBasicFieldState(
                                        index,
                                        value,
                                        FilterOptions.Location
                                    )
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}