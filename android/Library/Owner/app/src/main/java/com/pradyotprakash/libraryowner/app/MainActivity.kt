package com.pradyotprakash.libraryowner.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.pradyotprakash.libraryowner.app.theme.LibraryOwnerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LibraryOwnerTheme(
                dynamicColor = false
            ) {}
        }
    }
}
