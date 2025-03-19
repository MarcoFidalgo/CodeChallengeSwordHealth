package com.marcofidalgo.feature.details.viewmodel

import androidx.lifecycle.ViewModel
import com.marcofidalgo.feature.catslist.data.remote.CatBreed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor() : ViewModel() {

    private val _selectedCatBreed = MutableStateFlow<CatBreed?>(null)
    val selectedCatBreed: StateFlow<CatBreed?> = _selectedCatBreed

    fun updateSelectedBreed(newValue: CatBreed) {
        _selectedCatBreed.value = newValue
    }
}