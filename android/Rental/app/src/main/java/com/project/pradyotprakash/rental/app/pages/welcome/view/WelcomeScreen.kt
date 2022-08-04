package com.project.pradyotprakash.rental.app.pages.welcome.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.app.pages.welcome.viewmodel.WelcomeViewModel
import com.project.pradyotprakash.rental.app.utils.UserType

/**
 * A welcome screen for the user, this will help the user to
 * select the option to authenticate themselves.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(
    welcomeViewModel: WelcomeViewModel,
    userType: UserType
) {
    Column {
        SmallTopAppBar(
            title = {},
            colors = TopAppBarDefaults.smallTopAppBarColors(),
            navigationIcon = {
                IconButton(onClick = welcomeViewModel::navigateBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = Icons.Default.Phone.name,
                    )
                }
            },
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .padding(top = 10.dp, bottom = 20.dp),
        ) {
            Text(
                text = String.format(TR.welcomeMessage, userType.name),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                Button(
                    onClick = welcomeViewModel::goToInformationScreen,
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = Icons.Default.Phone.name,
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(TR.phone)
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
                Button(
                    onClick = welcomeViewModel::goToInformationScreen,
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = Icons.Default.Phone.name,
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(TR.email)
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = welcomeViewModel::goToInformationScreen,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row {
                    Text(TR.signInWithGoogle)
                }
            }
        }
    }
}