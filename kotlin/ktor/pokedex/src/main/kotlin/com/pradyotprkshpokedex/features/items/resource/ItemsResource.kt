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
    data class Id(private val parent: ItemsResource = ItemsResource(), val id: Int) {
        val isValid: Boolean
            get() = id > 0
    }

    /**
     * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
     */
    @Resource(Paths.Items.PAGINATION)
    data class Pagination(
        private val parent: ItemsResource = ItemsResource(), val offset: Int, val limit: Int,
        val withDetails: Boolean = false
    ) {
        val isValid: Boolean
            get() = offset >= 0 && limit >= 0
    }

    /**
     * Item attributes define particular aspects of items, e.g. "usable in battle" or "consumable".
     */
    @Resource(Paths.Items.ATTRIBUTE)
    class Attribute(private val parent: ItemsResource = ItemsResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(private val parent: Attribute = Attribute(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Items.PAGINATION)
        data class Pagination(
            private val parent: Attribute = Attribute(), val offset: Int, val limit: Int,
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
    class Category(private val parent: ItemsResource = ItemsResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(private val parent: Category = Category(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Items.PAGINATION)
        data class Pagination(
            private val parent: Category = Category(), val offset: Int, val limit: Int,
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
    class FilingEffect(private val parent: ItemsResource = ItemsResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(private val parent: FilingEffect = FilingEffect(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Items.PAGINATION)
        data class Pagination(
            private val parent: FilingEffect = FilingEffect(), val offset: Int, val limit: Int,
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
    class Pockets(private val parent: ItemsResource = ItemsResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(private val parent: Pockets = Pockets(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Items.PAGINATION)
        data class Pagination(
            private val parent: Pockets = Pockets(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }
}