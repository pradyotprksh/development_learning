package com.pradyotprkshpokedex.utils

object Paths {
    const val ID = "id"
    const val OFFSET = "offset"
    const val LIMIT = "limit"

    object Berries {
        const val BERRY = "/berry"
        const val BERRY_PAGINATION = "{${OFFSET}}/{${LIMIT}}"
        const val BERRY_ID = "{${ID}?}"
        const val FIRMNESS = "/berry-firmness"
        const val FLAVOR = "/berry-flavor"
    }

    object Contests {}
    object Encounters {}
    object Evolution {}
    object Games {}
    object Items {}
    object Locations {}
    object Machines {}
    object Moves {}
    object Pokemon {}
}