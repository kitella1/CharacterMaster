package com.example.charactermaster

import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class DnDRetriever {
    fun get5eClasses(callback: Callback<DnDData>) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.dnd5eapi.co/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service = retrofit.create(DnDService::class.java)
        val call= service.use5eService()
        call.enqueue(callback)
    }
}
