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
import com.pradyotprakash.customannotations.data.Car
import com.pradyotprakash.customannotations.data.Person
import com.pradyotprakash.customannotations.ui.theme.CustomAnnotationsTheme
import com.pradyotprakash.customannotations.utils.JsonUtil

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CustomAnnotationsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val person = Person(
                        firstName = "Pradyot",
                        lastName = "Prakash",
                        cars = listOf(
                            Car(
                                model = "Tesla Model 3",
                                licenseNumber = "ABC123",
                            ),
                            Car(
                                model = "Tesla Model Y",
                                licenseNumber = "XYZ456",
                            ),
                            Car(
                                model = "Tesla Model S",
                                licenseNumber = "LMN789",
                            ),
                            Car(
                                model = "Tesla Model X",
                                licenseNumber = "PQR012",
                            ),
                        )
                    )

                    val jsonFormat = JsonUtil.toJson(person)

                    Greeting(
                        name = jsonFormat,
                        modifier = Modifier.padding(innerPadding)
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