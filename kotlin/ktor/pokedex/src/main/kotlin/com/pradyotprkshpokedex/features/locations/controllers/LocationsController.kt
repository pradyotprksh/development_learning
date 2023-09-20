package com.pradyotprkshpokedex.features.locations.controllers

class LocationsController(
    private val locationAreasController: LocationAreasController,
    private val locationController: LocationController,
    private val palPakAreasController: PalPakAreasController,
    private val regionsController: RegionsController,
): LocationAreasController by locationAreasController,
LocationController by locationController,
PalPakAreasController by palPakAreasController,
RegionsController by regionsController {
}