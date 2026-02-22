package org.example.Data_layer.Model

data class Station(
    val id: Int,
    val name: String,
    val line: String,
    val order: Int,
    val is_transfer: Boolean)



data class StationsResponse(
    val stations: List<Station>
)