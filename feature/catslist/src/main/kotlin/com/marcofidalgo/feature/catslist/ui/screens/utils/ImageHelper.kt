package com.marcofidalgo.feature.catslist.ui.screens.utils

import com.marcofidalgo.network.api.Constants.URL_CAT_IMAGE

object ImageHelper {
    fun getCatImage(breedId: String): String =
        String.format(
            URL_CAT_IMAGE,
            breedId,
        )
}