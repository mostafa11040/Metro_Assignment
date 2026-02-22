package org.example

import org.example.Data_layer.Repo.StationsRepo
import kotlin.math.abs

fun main() {

    val stationsRepo= StationsRepo()

    val stationsResponse = stationsRepo.stationsResponse

    print("Enter First Metro name : ")
    val firstInput = readlnOrNull()?.trim()?.lowercase() ?: ""

    print("Enter Second Metro name : ")
    val secondInput = readlnOrNull()?.trim()?.lowercase() ?: ""

    val startStation = stationsResponse.stations.find {
        it.name.trim().lowercase() == firstInput
    }

    val endStation = stationsResponse.stations.find {
        it.name.trim().lowercase() == secondInput
    }

    if (startStation == null || endStation == null) {
        println("Station not found!")
        return
    }


    val lineStations = stationsResponse
        .stations.filter { it.line == startStation.line }
        .sortedBy { it.order }

    val startIndex = lineStations.indexOf(startStation)
    val endIndex = lineStations.indexOf(endStation)
    val numberOfStations = abs(startIndex - endIndex)
    println("Number of stations: $numberOfStations")

    val route =
        if (startIndex <= endIndex)
            lineStations.subList(startIndex, endIndex + 1)
        else
            lineStations.subList(endIndex, startIndex + 1).reversed()

    println("*************************************")
    println("*     Welcome To Cairo Metro        *")
    println("*************************************")
    route.forEach {
        println("- ${it.name} { ${it.line} }")
    }

    val price = when {
        numberOfStations <= 9 -> 8
        numberOfStations <= 16 -> 10
        numberOfStations <= 23 -> 15
        else -> 20
    }

    println("Ticket price: $price EGP")
}