package com.pradyotprkshpokedex.features.berries.controllers

class BerriesController(
    private val berryController: BerryController,
    private val berryFirmnessController: BerryFirmnessController,
    private val berryFlavorController: BerryFlavorController,
) : BerryController by berryController, BerryFirmnessController by berryFirmnessController,
    BerryFlavorController by berryFlavorController