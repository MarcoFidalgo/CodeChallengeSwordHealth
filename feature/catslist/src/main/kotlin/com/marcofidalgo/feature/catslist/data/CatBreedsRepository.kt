package com.marcofidalgo.feature.catslist.data

import com.marcofidalgo.feature.catslist.data.remote.BreedsApiService
import com.marcofidalgo.feature.catslist.data.remote.CatBreed
import com.marcofidalgo.network.api.Constants
import javax.inject.Inject

class CatBreedsRepository @Inject constructor(
    private val apiService: BreedsApiService
) {

    suspend fun getCatBreeds(): List<CatBreed> {
        val response = apiService.getCatBreeds(apiKey = Constants.CAT_API_KEY)
        return response
    }

    suspend fun searchCatBreed(breedName: String): List<CatBreed> {
        val response =
            apiService.searchCatBreed(breedName = breedName, apiKey = Constants.CAT_API_KEY)
        return response
    }
}