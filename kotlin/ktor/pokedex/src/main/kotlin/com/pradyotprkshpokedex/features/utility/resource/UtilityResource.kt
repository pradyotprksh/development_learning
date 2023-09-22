package com.pradyotprkshpokedex.features.utility.resource

import com.pradyotprkshpokedex.utils.Paths
import io.ktor.resources.Resource

@Resource(Paths.Utility.UTILITY)
class UtilityResource {
    @Resource(Paths.Utility.ALL_POKEMON_IMAGES)
    data class AllPokemonImages(private val parent: UtilityResource = UtilityResource())
}