package com.pradyotprakash.personalblog

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.pradyotprakash.personalblog.app.PersonalBlogApp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        PersonalBlogApp()
    }
}