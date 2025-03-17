package com.marcofidalgo.feature.catslist.viewmodel

import androidx.lifecycle.ViewModel
import com.marcofidalgo.feature.catslist.domain.usecases.ObserveCatsBreedsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatBreedsViewModel @Inject constructor(
    private val observeCatsBreedsUseCase: ObserveCatsBreedsUseCase
): ViewModel() {

    val catBreedsList = observeCatsBreedsUseCase.observeCatBreeds()
}