package com.pradyotprakash.findingfalcone.domain.entity

typealias Planets = List<PlanetsEntity>

data class PlanetsEntity(
    val name: String,
    val distance: Long,
    var selected: Boolean = false,
    var vehicleDetails: VehiclesEntity? = null,
)
