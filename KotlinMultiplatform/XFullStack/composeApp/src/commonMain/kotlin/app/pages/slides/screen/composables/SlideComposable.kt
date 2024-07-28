package app.pages.slides.screen.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.composables.NetworkImageComposable
import app.pages.slides.utils.backgroundColor
import app.pages.slides.utils.textAlignment
import app.pages.slides.utils.textColor
import app.pages.slides.utils.textStyle
import data.device.slides.Slides

@Composable
fun SlideComposable(
    modifier: Modifier = Modifier,
    slide: Slides,
) {
    Column(
        modifier = modifier
            .background(color = slide.background.backgroundColor())
            .padding(15.dp),
    ) {
        slide.title?.let { title ->
            Text(
                title.value,
                style = title.styles?.style?.textStyle() ?: LocalTextStyle.current,
                color = title.styles?.color?.textColor()
                    ?: LocalContentColor.current,
                modifier = Modifier.fillMaxWidth(),
                textAlign = title.styles?.alignment?.textAlignment(),
            )
        }
        slide.subtitle?.let { subtitle ->
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                subtitle.value,
                style = subtitle.styles?.style?.textStyle()
                    ?: LocalTextStyle.current,
                color = subtitle.styles?.color?.textColor()
                    ?: LocalContentColor.current,
                modifier = Modifier.fillMaxWidth(),
                textAlign = subtitle.styles?.alignment?.textAlignment(),
            )
        }
        slide.bottomImage?.let { image ->
            Spacer(modifier = Modifier.weight(1f))
            NetworkImageComposable(
                url = image.value,
                contentScale = ContentScale.Inside,
                backgroundColor = slide.background.backgroundColor(),
                modifier = Modifier.height(image.styles?.height?.dp ?: Dp.Unspecified)
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        slide.footer?.let { footer ->
            Spacer(modifier = Modifier.weight(1f))
            Text(
                footer.value,
                style = footer.styles?.style?.textStyle()
                    ?: LocalTextStyle.current,
                color = footer.styles?.color?.textColor()
                    ?: LocalContentColor.current,
                modifier = Modifier.fillMaxWidth(),
                textAlign = footer.styles?.alignment?.textAlignment(),
            )
        }
    }
}