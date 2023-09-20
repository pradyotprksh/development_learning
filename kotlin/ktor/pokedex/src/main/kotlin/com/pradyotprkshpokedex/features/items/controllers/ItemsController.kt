package com.pradyotprkshpokedex.features.items.controllers

class ItemsController(
    private val itemAttributesController: ItemAttributesController,
    private val itemCategoriesController: ItemCategoriesController,
    private val itemController: ItemController,
    private val itemFilingEffectsController: ItemFilingEffectsController,
    private val itemPocketsController: ItemPocketsController,
) : ItemAttributesController by itemAttributesController, ItemCategoriesController by itemCategoriesController,
    ItemController by itemController, ItemFilingEffectsController by itemFilingEffectsController,
    ItemPocketsController by itemPocketsController {
}