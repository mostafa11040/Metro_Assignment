package org.example.Data_layer.Repo

import com.google.gson.Gson
import org.example.Data_layer.Model.StationsResponse
import java.io.File

class StationsRepo(){
    var stationsResponse: StationsResponse = Gson().fromJson(
        File("D:\\projects\\Metro_Assignment\\src\\main\\kotlin\\Data_layer\\Repo\\cairo_metro_structured.json").readText(),
        StationsResponse::class.java
    )
}