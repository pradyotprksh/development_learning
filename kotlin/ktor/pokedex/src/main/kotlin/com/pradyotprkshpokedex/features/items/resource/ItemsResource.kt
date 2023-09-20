package com.pradyotprkshpokedex.features.items.resource

import com.pradyotprkshpokedex.utils.Paths
import io.ktor.resources.Resource

/**
 * An item is an object in the games which the player can pick up, keep in their bag, and use in some manner. They
 * have various uses, including healing, powering up, helping catch Pokémon, or to access a new area.
 */
@Resource(Paths.Items.ITEM)
class ItemsResource {
    @Resource("{${Paths.Parameters.ID}}")
    data class Id(val parent: ItemsResource = ItemsResource(), val id: Int) {
        val isValid: Boolean
            get() = id > 0
    }

    /**
     * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
     */
    @Resource(Paths.Items.PAGINATION)
    data class Pagination(
        val parent: ItemsResource = ItemsResource(), val offset: Int, val limit: Int,
        val withDetails: Boolean = false
    ) {
        val isValid: Boolean
            get() = offset >= 0 && limit >= 0
    }

    /**
     * Item attributes define particular aspects of items, e.g. "usable in battle" or "consumable".
     */
    @Resource(Paths.Items.ATTRIBUTE)
    class Attribute(val parent: ItemsResource = ItemsResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(val parent: Attribute = Attribute(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Items.PAGINATION)
        data class Pagination(
            val parent: Attribute = Attribute(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    /**
     * Item categories determine where items will be placed in the players bag.
     */
    @Resource(Paths.Items.CATEGORY)
    class Category(val parent: ItemsResource = ItemsResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(val parent: Category = Category(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Items.PAGINATION)
        data class Pagination(
            val parent: Category = Category(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    /**
     * The various effects of the move "Fling" when used with different items.
     */
    @Resource(Paths.Items.FILING_EFFECT)
    class FilingEffect(val parent: ItemsResource = ItemsResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(val parent: FilingEffect = FilingEffect(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Items.PAGINATION)
        data class Pagination(
            val parent: FilingEffect = FilingEffect(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    /**
     * Pockets within the players bag used for storing items by category.
     */
    @Resource(Paths.Items.POCKETS)
    class Pockets(val parent: ItemsResource = ItemsResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(val parent: Pockets = Pockets(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Items.PAGINATION)
        data class Pagination(
            val parent: Pockets = Pockets(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }
}