package com.pradyotprakash.findingfalcone.domain.entity

typealias Vehicles = List<VehiclesEntity>

data class VehiclesEntity(
    val name: String,
    var total_no: Long,
    val max_distance: Long,
    val speed: Long
)
