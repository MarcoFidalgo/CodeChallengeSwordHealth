package com.marcofidalgo.feature.home.navigation

import androidx.navigation.NavHostController
import com.marcofidalgo.feature.home.ui.components.BottomTab
import kotlinx.coroutines.flow.MutableStateFlow

class ScreensNavigator {

    private lateinit var parentNavController: NavHostController

    val currentBottomTab = MutableStateFlow<BottomTab?>(null)

    fun toTab(bottomTab: Any) {
        parentNavController.navigate(bottomTab)
    }

    fun setParentNavController(navController: NavHostController) {
        parentNavController = navController
    }
}
