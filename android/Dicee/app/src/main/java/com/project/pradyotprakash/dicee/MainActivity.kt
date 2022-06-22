package com.project.pradyotprakash.dicee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.pradyotprakash.dicee.ui.theme.ButtonColor
import com.project.pradyotprakash.dicee.ui.theme.DiceeTheme

class TapViewModel: ViewModel() {
    private val _diceFirst = MutableLiveData(R.mipmap.dice1)
    val diceFirst: LiveData<Int> = _diceFirst
    private val _diceSecond = MutableLiveData(R.mipmap.dice2)
    val diceSecond: LiveData<Int> = _diceSecond

    fun updateDices() {
        val randomNum1 = (1..6).random()
        val randomNum2 = (1..6).random()
        when (randomNum1) {
            1 -> _diceFirst.value = R.mipmap.dice1
            2 -> _diceFirst.value = R.mipmap.dice2
            3 -> _diceFirst.value = R.mipmap.dice3
            4 -> _diceFirst.value = R.mipmap.dice4
            5 -> _diceFirst.value = R.mipmap.dice5
            6 -> _diceFirst.value = R.mipmap.dice6
        }
        when (randomNum2) {
            1 -> _diceSecond.value = R.mipmap.dice1
            2 -> _diceSecond.value = R.mipmap.dice2
            3 -> _diceSecond.value = R.mipmap.dice3
            4 -> _diceSecond.value = R.mipmap.dice4
            5 -> _diceSecond.value = R.mipmap.dice5
            6 -> _diceSecond.value = R.mipmap.dice6
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Box {
                        BackgroundImage(id = R.mipmap.newbackground)
                        TopLayer()
                    }
                }
            }
        }
    }
}

@Composable
fun TopLayer(tapViewModel: TapViewModel = viewModel()) {
    val firstDice: Int by tapViewModel.diceFirst.observeAsState(R.mipmap.dice1)
    val secondDice: Int by tapViewModel.diceSecond.observeAsState(R.mipmap.dice1)

    Column(
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AssetImage(id = R.mipmap.diceelogo, size = 300.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            AssetImage(id = firstDice, size = 150.dp)
            AssetImage(id = secondDice, size = 150.dp)
        }
        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick = {tapViewModel.updateDices()},
            modifier = Modifier.fillMaxWidth(fraction = 0.4f),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = ButtonColor,
                contentColor = Color.White
            )
        ) {
            Text(text = "Tap")
        }
    }
}

@Composable
fun AssetImage(id: Int, size: Dp) {
    Image(
        painter = painterResource(id = id),
        modifier = Modifier.size(size),
        contentScale = ContentScale.Fit,
        contentDescription = "Asset composable"
    )
}

@Composable
fun BackgroundImage(id: Int) {
    Image(
        painter = painterResource(id = id),
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds,
        contentDescription = "Background Image"
    )
}

@Preview(showBackground = true)
@Composable
fun BackgroundImagePreview() {
    DiceeTheme {
        BackgroundImage(id = R.mipmap.newbackground)
    }
}