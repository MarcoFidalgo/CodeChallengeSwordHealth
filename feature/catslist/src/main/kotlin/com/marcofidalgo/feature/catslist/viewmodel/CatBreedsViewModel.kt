package com.marcofidalgo.feature.catslist.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcofidalgo.feature.catslist.data.CatBreedsRepository
import com.marcofidalgo.feature.catslist.data.remote.CatBreed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatBreedsViewModel @Inject constructor(
    private val catBreedsRepository: CatBreedsRepository
) : ViewModel() {

    private val _catBreeds = MutableStateFlow<List<CatBreed>>(emptyList())
    val catBreeds: StateFlow<List<CatBreed>> = _catBreeds

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        getCatBreeds()
    }

    private fun getCatBreeds() {
        viewModelScope.launch {
            try {
                _catBreeds.value = catBreedsRepository.getCatBreeds()
                Log.d("CATS_::", "Nº of cat breeds: ${_catBreeds.value.size}")
                _isLoading.value = false
            } catch (e: Exception) {
                Log.e("ERROR", "getCatBreeds: ${e.message}")
            }
        }
    }
}