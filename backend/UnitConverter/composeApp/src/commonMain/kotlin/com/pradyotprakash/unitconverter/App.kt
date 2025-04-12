package com.pradyotprakash.unitconverter

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.pradyotprakash.unitconverter.app.UnitConverterApp
import com.pradyotprakash.unitconverter.utils.Localization
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        UnitConverterApp()
    }
}