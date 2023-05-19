package com.pradyotprakash.findingfalcone.domain.entity

typealias Vehicles = List<VehiclesEntity>

data class VehiclesEntity(
    val name: String,
    val totalNo: Long,
    val maxDistance: Long,
    val speed: Long
)
