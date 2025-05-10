package com.pradyotprakash.customannotations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pradyotprakash.customannotations.data.CarRuntime
import com.pradyotprakash.customannotations.data.PersonCompileTime
import com.pradyotprakash.customannotations.data.PersonRuntime
import com.pradyotprakash.customannotations.ui.theme.CustomAnnotationsTheme
import com.pradyotprakash.customannotations.utils.JsonUtil
import com.pradyotprakash.toJson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CustomAnnotationsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val personRuntime = PersonRuntime(
                        firstName = "Compile Time",
                        lastName = "Last",
                        cars = listOf(
                            CarRuntime(
                                model = "Tesla Model 3",
                                licenseNumber = "ABC123",
                            ),
                            CarRuntime(
                                model = "Tesla Model Y",
                                licenseNumber = "XYZ456",
                            ),
                            CarRuntime(
                                model = "Tesla Model S",
                                licenseNumber = "LMN789",
                            ),
                            CarRuntime(
                                model = "Tesla Model X",
                                licenseNumber = "PQR012",
                            ),
                            CarRuntime(
                                model = "Tesla Roadster",
                                licenseNumber = "DEF345",
                            ),
                        )
                    )

                    val personCompileTime = PersonCompileTime(
                        firstName = "Runtime",
                        lastName = "Last",
                        cars = listOf(
                            CarRuntime(
                                model = "Tesla Model 3",
                                licenseNumber = "ABC123",
                            ),
                            CarRuntime(
                                model = "Tesla Model Y",
                                licenseNumber = "XYZ456",
                            ),
                            CarRuntime(
                                model = "Tesla Model S",
                                licenseNumber = "LMN789",
                            ),
                            CarRuntime(
                                model = "Tesla Model X",
                                licenseNumber = "PQR012",
                            ),
                            CarRuntime(
                                model = "Tesla Roadster",
                                licenseNumber = "DEF345",
                            ),
                        )
                    )

                    Greeting(
                        name = "Runtime Result:\n${JsonUtil.toJson(personRuntime)} \n\n" +
                                "Compile Time Result:\n${personCompileTime.toJson()}",
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(10.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CustomAnnotationsTheme {
        Greeting("Android")
    }
}