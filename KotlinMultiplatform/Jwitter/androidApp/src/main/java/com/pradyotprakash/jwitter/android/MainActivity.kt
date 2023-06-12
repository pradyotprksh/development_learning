package com.pradyotprakash.jwitter.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.pradyotprakash.jwitter.di.DiFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LaunchedEffect(key1 = true) {
                        print("PRADYOT: =================")
                        try {
                            val data = DiFactory.userPresenter.createUser(
                                email = "pradyot@gmail.com",
                                password = "123456789"
                            )
                            print("PRADYOT: $data")
                        } catch (e: Exception) {
                            print("PRADYOT: $e")
                        }
                        print("PRADYOT: =================")
                    }
                }
            }
        }
    }
}
