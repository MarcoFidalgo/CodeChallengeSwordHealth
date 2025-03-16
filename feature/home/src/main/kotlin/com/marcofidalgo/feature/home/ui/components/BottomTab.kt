package com.marcofidalgo.feature.home.ui.components

import kotlinx.serialization.Serializable

@Serializable
sealed class BottomTab(var title: String) {
    @Serializable
    data class CatsList(val titleCatsList: String) : BottomTab(titleCatsList)

    @Serializable
    data class Favourites(val titleFavourites: String) : BottomTab(titleFavourites)
}

