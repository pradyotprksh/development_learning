package utils

import org.jetbrains.compose.resources.DrawableResource
import xfullstack.composeapp.generated.resources.Res
import xfullstack.composeapp.generated.resources.logo_dark
import xfullstack.composeapp.generated.resources.logo_light

enum class Resources(
    val resource: DrawableResource,
    val contentDescription: String = "",
) {
    LogoDark(
        resource = Res.drawable.logo_dark,
        contentDescription = "LogoDark of the application"
    ),
    LogoLight(
        resource = Res.drawable.logo_light,
        contentDescription = "LogoLight of the application"
    ),
}