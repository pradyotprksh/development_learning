package com.pradyotprakash.exchangerate.app.pages.home.view.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.pradyotprakash.exchangerate.app.utils.ContentDescription.CURRENCY_MENU
import com.pradyotprakash.exchangerate.app.utils.ContentDescription.MENU_ITEM

@Composable
fun AmountEntryComposable(
    amountText: String,
    selectedCurrency: String,
    currencyMenuVisible: Boolean,
    allCurrencies: Map<String, String>,
    updateAmountValue: (String) -> Unit,
    updateMenuVisibility: () -> Unit,
    updateSelectedCurrency: (String) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        TextField(
            value = amountText,
            onValueChange = updateAmountValue,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.None
            ),
            modifier = Modifier.weight(0.7f),
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Button(onClick = updateMenuVisibility) {
                Text(text = selectedCurrency)
            }
            DropdownMenu(
                expanded = currencyMenuVisible,
                onDismissRequest = updateMenuVisibility,
                properties = PopupProperties(
                    focusable = true,
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true,
                ),
                modifier = Modifier.semantics { contentDescription = CURRENCY_MENU }
            ) {
                allCurrencies.forEach {
                    DropdownMenuItem(
                        text = {
                            Text(text = it.key)
                        },
                        onClick = {
                            updateSelectedCurrency(it.key)
                            updateMenuVisibility()
                        },
                        modifier = Modifier.semantics { contentDescription = MENU_ITEM }
                    )
                }
            }
        }
    }
}