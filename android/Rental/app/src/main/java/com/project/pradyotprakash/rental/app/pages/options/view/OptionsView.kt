package com.project.pradyotprakash.rental.app.pages.options.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.app.pages.options.viewmodel.OptionsViewModel
import com.project.pradyotprakash.rental.app.utils.Assets

/**
 * An option view which will help the user to choose between owner and renter.
 */
@Composable
fun OptionsView(optionsViewModel: OptionsViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        IconButton(
            onClick = {
                optionsViewModel.showMoreInfo()
            },
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = Icons.Default.Info.name,
                tint = MaterialTheme.colorScheme.primary
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
                .padding(top = 30.dp, bottom = 20.dp),
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = Assets.AppIcon.resourceId),
                contentDescription = Assets.AppIcon.imageDescription,
                modifier = Modifier.width(250.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    optionsViewModel.navigateToOwnerFlow()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = TR.owner)
            }
            OutlinedButton(
                onClick = {
                    optionsViewModel.navigateToRenterFlow()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = TR.renter)
            }
        }
    }
}