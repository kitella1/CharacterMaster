package com.example.charactermaster

import retrofit2.Call
import retrofit2.http.GET

interface DnDService {
    //details at http://square.github.io/retrofit/
    @GET("api/classes")
    fun use5eService(): Call<DnDData>
}
