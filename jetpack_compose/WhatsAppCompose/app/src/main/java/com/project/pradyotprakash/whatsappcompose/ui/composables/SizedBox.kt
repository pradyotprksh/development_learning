package com.project.pradyotprakash.whatsappcompose.ui.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * A sized box which will be used to add a spacer between different Composable.
 *
 * [sizeFloat], [heightFloat] and [widthFloat] are all optional value. Depending on what the value is the
 * value will be set. This will be the fraction of the size.
 *
 * [size], [height] and [width] are the fix values boxes.
 */

@Composable
fun SizedBox(
    sizeFloat: Float? = null,
    heightFloat: Float? = null,
    widthFloat: Float? = null,
    size: Int? = null,
    height: Int? = null,
    width: Int? = null
) {
    val isFix = size != null || height != null || width != null

    if (isFix) {
        if (size != null) {
            Spacer(modifier = Modifier.size(size.dp))
        } else if (height != null && width != null) {
            Spacer(modifier = Modifier
                .height(height.dp)
                .width(width.dp))
        } else if (height != null) {
            Spacer(modifier = Modifier.height(height.dp))
        } else if (width != null) {
            Spacer(modifier = Modifier.width(width.dp))
        } else {
            Spacer(modifier = Modifier.fillMaxSize(1f))
        }
    } else {
        if (sizeFloat != null) {
            Spacer(modifier = Modifier.fillMaxSize(sizeFloat))
        } else if (heightFloat != null && widthFloat != null) {
            Spacer(modifier = Modifier
                .fillMaxHeight(heightFloat)
                .fillMaxWidth(widthFloat))
        } else if (heightFloat != null) {
            Spacer(modifier = Modifier.fillMaxHeight(heightFloat))
        } else if (widthFloat != null) {
            Spacer(modifier = Modifier.fillMaxWidth(widthFloat))
        } else {
            Spacer(modifier = Modifier.fillMaxSize(1f))
        }
    }
}