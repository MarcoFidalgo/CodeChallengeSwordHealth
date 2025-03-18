package com.marcofidalgo.feature.catslist.data.remote

import com.marcofidalgo.network.api.Constants
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface BreedsApiService {
    @GET("breeds")
    suspend fun getCatBreeds(
        @Query("limit") limit: Int = 100,
        @Query("page") page: Int = 0,
        @Header("x-api-key") apiKey: String = Constants.CAT_API_KEY
    ): List<CatBreed>

    @GET("breeds/search")
    suspend fun searchCatBreed(
        @Query("q") breedName: String,
        @Query("attach_image") attachImage: Int = 0, // 1 => whether to attach the reference_image_id image or not
        @Header("x-api-key") apiKey: String = Constants.CAT_API_KEY
    ): List<CatBreed>

}