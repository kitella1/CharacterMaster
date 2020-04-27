package com.example.charactermaster.mapAPI
import com.squareup.moshi.Json


data class MapData(
    @Json(name = "html_attributions")
    val htmlAttributions: List<Any> = listOf(),
    @Json(name = "next_page_token")
    val nextPageToken: String = "",
    @Json(name = "results")
    val results: List<Result> = listOf(),
    @Json(name = "status")
    val status: String = ""
)

data class Result(
    @Json(name = "business_status")
    val businessStatus: String = "",
    @Json(name = "geometry")
    val geometry: Geometry = Geometry(),
    @Json(name = "icon")
    val icon: String = "",
    @Json(name = "id")
    val id: String = "",
    @Json(name = "name")
    val name: String = "",
    @Json(name = "opening_hours")
    val openingHours: OpeningHours = OpeningHours(),
    @Json(name = "photos")
    val photos: List<Photo> = listOf(),
    @Json(name = "place_id")
    val placeId: String = "",
    @Json(name = "plus_code")
    val plusCode: PlusCode = PlusCode(),
    @Json(name = "price_level")
    val priceLevel: Int = 0,
    @Json(name = "rating")
    val rating: Double = 0.0,
    @Json(name = "reference")
    val reference: String = "",
    @Json(name = "scope")
    val scope: String = "",
    @Json(name = "types")
    val types: List<String> = listOf(),
    @Json(name = "user_ratings_total")
    val userRatingsTotal: Int = 0,
    @Json(name = "vicinity")
    val vicinity: String = ""
)

data class Geometry(
    @Json(name = "location")
    val location: Location = Location(),
    @Json(name = "viewport")
    val viewport: Viewport = Viewport()
)

data class OpeningHours(
    @Json(name = "open_now")
    val openNow: Boolean = false
)

data class Photo(
    @Json(name = "height")
    val height: Int = 0,
    @Json(name = "html_attributions")
    val htmlAttributions: List<String> = listOf(),
    @Json(name = "photo_reference")
    val photoReference: String = "",
    @Json(name = "width")
    val width: Int = 0
)

data class PlusCode(
    @Json(name = "compound_code")
    val compoundCode: String = "",
    @Json(name = "global_code")
    val globalCode: String = ""
)

data class Location(
    @Json(name = "lat")
    val lat: Double = 0.0,
    @Json(name = "lng")
    val lng: Double = 0.0
)

data class Viewport(
    @Json(name = "northeast")
    val northeast: Northeast = Northeast(),
    @Json(name = "southwest")
    val southwest: Southwest = Southwest()
)

data class Northeast(
    @Json(name = "lat")
    val lat: Double = 0.0,
    @Json(name = "lng")
    val lng: Double = 0.0
)

data class Southwest(
    @Json(name = "lat")
    val lat: Double = 0.0,
    @Json(name = "lng")
    val lng: Double = 0.0
)