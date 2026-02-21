package org.example

import com.google.gson.Gson
import org.example.Domain.StationsResponse
import java.io.File
import kotlin.math.abs

fun main() {

    val stationsResponse = Gson().fromJson(
        File("D:\\projects\\Metro_Assignment\\src\\main\\kotlin\\Domain\\cairo_metro_structured.json").readText(),
        StationsResponse::class.java
    )

    val stations = stationsResponse.stations

    print("Enter First Metro name : ")
    val firstInput = readlnOrNull()?.trim()?.lowercase() ?: ""

    print("Enter Second Metro name : ")
    val secondInput = readlnOrNull()?.trim()?.lowercase() ?: ""

    val startStation = stations.find {
        it.name.trim().lowercase() == firstInput
    }

    val endStation = stations.find {
        it.name.trim().lowercase() == secondInput
    }

    if (startStation == null || endStation == null) {
        println("Station not found!")
        return
    }


    val lineStations = stations
        .filter { it.line == startStation.line }
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