package com.example.charactermaster.mapAPI

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface MapService {
    @GET("place/nearbysearch/json")
    fun useMapService(@Query("location") location: String, @Query("radius") radius: Int, @Query("keyword") keyword: String, @Query("key") key:String): Call<MapData>
}
