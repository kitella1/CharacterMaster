package com.example.charactermaster.mapAPI

import android.util.Log
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MapRetriever {
    fun getNearbyPlaces(callback: Callback<MapData>, lat: Double, long:Double, radius:Int, keyword:String, key:String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/maps/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service = retrofit.create(MapService::class.java)
        val call= service.useMapService("$lat,$long", radius, keyword, key)
        call.enqueue(callback)
    }
}