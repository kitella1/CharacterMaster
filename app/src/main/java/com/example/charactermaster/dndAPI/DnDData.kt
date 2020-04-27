package com.example.charactermaster
import com.squareup.moshi.Json

data class DnDData(
    @Json(name = "count")
    val count: Int = 0,
    @Json(name = "results")
    val results: List<Result> = listOf()
)

data class Result(
    @Json(name = "index")
    val index: String = "",
    @Json(name = "name")
    val name: String = "",
    @Json(name = "url")
    val url: String = ""
)