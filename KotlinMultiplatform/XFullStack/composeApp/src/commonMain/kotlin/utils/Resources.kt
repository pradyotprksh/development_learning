package utils

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import xfullstack.composeapp.generated.resources.Res
import xfullstack.composeapp.generated.resources.logo

enum class Resources @OptIn(ExperimentalResourceApi::class) constructor(
    val resource: DrawableResource,
    val contentDescription: String = "",
) {
    @OptIn(ExperimentalResourceApi::class)
    Logo(
        resource = Res.drawable.logo,
        contentDescription = "Logo of the application"
    ),
}