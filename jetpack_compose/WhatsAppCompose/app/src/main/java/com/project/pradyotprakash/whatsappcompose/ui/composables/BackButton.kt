package com.project.pradyotprakash.whatsappcompose.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.project.pradyotprakash.whatsappcompose.R
import com.project.pradyotprakash.whatsappcompose.ui.theme.Action

/**
 * A back button composable which will be used to show a back option whenever necessary.
 */

@Composable
fun BackButton(back: () -> Unit, icon: ImageVector, topSpace: Int = 50) {
    Column(
        modifier = Modifier
            .padding(start = 15.dp)
    ) {
        SizedBox(height = topSpace)
        Box(
            modifier = Modifier
                .size(35.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .border(width = 2.dp, color = Action, shape = RoundedCornerShape(10.dp))
        ) {
            IconButton(
                onClick = {
                    back()
                }
            ) {
                Icon(
                    icon,
                    contentDescription = stringResource(id = R.string.image_description),
                    modifier = Modifier.size(25.dp),
                    tint = Color.Black
                )
            }
        }
    }
}