package com.pradyotprkshpokedex.features.berries.resource

import com.pradyotprkshpokedex.utils.Paths
import io.ktor.resources.*

@Resource(Paths.Berries.BERRY)
class Berry {
    @Resource("{${Paths.ID}}")
    data class Id(val parent: Berry = Berry(), val id: String)

    @Resource(Paths.Berries.PAGINATION)
    data class Pagination(val parent: Berry = Berry(), val offset: Int, val limit: Int)
}
