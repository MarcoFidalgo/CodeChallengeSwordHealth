package com.marcofidalgo.feature.catslist.domain.usecases

import com.marcofidalgo.feature.catslist.data.local.CatBreed
import com.marcofidalgo.feature.catslist.data.local.CatBreedsDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveCatsBreedsUseCase @Inject constructor(
    private val catBreedsDao: CatBreedsDao,
) {

    fun observeCatBreeds(): Flow<List<CatBreed>> {
        return catBreedsDao.observe()
    }
}