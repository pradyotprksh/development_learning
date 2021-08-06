package com.project.pradyotprakash.i_am_rich

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.pradyotprakash.i_am_rich.ui.theme.IAmRichTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IAmRichTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Title(heading = "I Am Rich")
                        Spacer(modifier = Modifier.height(20.dp))
                        Assets(id = R.mipmap.diamond)
                    }
                }
            }
        }
    }
}

@Composable
fun Title(heading: String) {
    Text(
        text = heading,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White
    )
}

@Composable
fun Assets(id: Int) {
    Image(
        painter = painterResource(id = id),
        contentScale = ContentScale.Fit,
        modifier = Modifier.size(350.dp),
        contentDescription = "Diamond image"
    )
}

@Preview(showBackground = false)
@Composable
fun DefaultPreviewTitle() {
    IAmRichTheme {
        Title(heading = "I Am Rich")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewAsset() {
    IAmRichTheme {
        Assets(id = R.mipmap.diamond)
    }
}