package utils

import org.jetbrains.compose.resources.DrawableResource
import xfullstack.composeapp.generated.resources.Res
import xfullstack.composeapp.generated.resources.logo

enum class Resources(
    val resource: DrawableResource,
    val contentDescription: String = "",
) {
    Logo(
        resource = Res.drawable.logo,
        contentDescription = "Logo of the application"
    ),
}