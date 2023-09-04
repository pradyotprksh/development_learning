package com.pradyotprkshpokedex.features.berries.resource

import com.pradyotprkshpokedex.utils.Paths
import io.ktor.resources.*

@Resource(Paths.Berries.BERRY)
data class BerriesPagination(val offset: Int, val limit: Int)

@Resource(Paths.Berries.ALL_BERRIES)
object AllBerries
