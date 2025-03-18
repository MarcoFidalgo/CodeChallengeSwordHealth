package com.marcofidalgo.feature.catslist.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CatBreed(
    @Json(name = "adaptability") val adaptability: Int?,
    @Json(name = "affection_level") val affection_level: Int?,
    @Json(name = "alt_names") val alt_names: String?,
    @Json(name = "bidability") val bidability: Int?,
    @Json(name = "cat_friendly") val cat_friendly: Int?,
    @Json(name = "cfa_url") val cfa_url: String?,
    @Json(name = "child_friendly") val child_friendly: Int?,
    @Json(name = "country_code") val country_code: String?,
    @Json(name = "country_codes") val country_codes: String?,
    @Json(name = "description") val description: String?,
    @Json(name = "dog_friendly") val dog_friendly: Int?,
    @Json(name = "energy_level") val energy_level: Int?,
    @Json(name = "experimental") val experimental: Int?,
    @Json(name = "grooming") val grooming: Int?,
    @Json(name = "hairless") val hairless: Int?,
    @Json(name = "health_issues") val health_issues: Int?,
    @Json(name = "hypoallergenic") val hypoallergenic: Int?,
    @Json(name = "id") val id: String?,
    @Json(name = "indoor") val indoor: Int?,
    @Json(name = "intelligence") val intelligence: Int?,
    @Json(name = "lap") val lap: Int?,
    @Json(name = "life_span") val life_span: String?,
    @Json(name = "name") val name: String?,
    @Json(name = "natural") val natural: Int?,
    @Json(name = "origin") val origin: String?,
    @Json(name = "rare") val rare: Int?,
    @Json(name = "reference_image_id") val reference_image_id: String?,
    @Json(name = "rex") val rex: Int?,
    @Json(name = "shedding_level") val shedding_level: Int?,
    @Json(name = "short_legs") val short_legs: Int?,
    @Json(name = "social_needs") val social_needs: Int?,
    @Json(name = "stranger_friendly") val stranger_friendly: Int?,
    @Json(name = "suppressed_tail") val suppressed_tail: Int?,
    @Json(name = "temperament") val temperament: String?,
    @Json(name = "vcahospitals_url") val vcahospitals_url: String?,
    @Json(name = "vetstreet_url") val vetstreet_url: String?,
    @Json(name = "vocalisation") val vocalisation: Int?,
    @Json(name = "weight") val weight: Weight?,
    @Json(name = "wikipedia_url") val wikipedia_url: String?
)

data class Weight(
    @Json(name = "imperial") val imperial: String?,
    @Json(name = "metric") val metric: String?
)