package com.marcofidalgo.feature.home.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.marcofidalgo.feature.catslist.ui.screens.CatsListScreen
import com.marcofidalgo.feature.favourites.ui.screens.FavouritesScreen
import com.marcofidalgo.feature.home.R
import com.marcofidalgo.feature.home.navigation.ScreensNavigator
import com.marcofidalgo.feature.home.ui.components.BottomTab
import com.marcofidalgo.feature.home.ui.components.BottomTabsBar

@Composable
fun HomeScreen() {

    val screensNavigator = remember() {
        ScreensNavigator()
    }

    val currentBottomTab = screensNavigator.currentBottomTab.collectAsState()

    Scaffold(
        bottomBar = {
            BottomAppBar(modifier = Modifier) {
                BottomTabsBar(
                    bottomTabs = listOf(
                        BottomTab.CatsList(stringResource(R.string.bottom_tab_title_cats_list)),
                        BottomTab.Favourites(stringResource(R.string.bottom_tab_title_favourites))
                    ),
                    currentBottomTab = currentBottomTab.value,
                    onTabClicked = { bottomTab ->
                        screensNavigator.toTab(bottomTab)
                    }
                )
            }
        },
        content = { padding ->
            HomeScreenContent(
                padding = padding,
                screensNavigator = screensNavigator,
            )
        })
}

@Composable
fun HomeScreenContent(
    padding: PaddingValues,
    screensNavigator: ScreensNavigator,
) {
    val parentNavController = rememberNavController()
    screensNavigator.setParentNavController(parentNavController)

    Surface(
        modifier = Modifier
            .padding(padding)
            .padding(horizontal = 12.dp)
    ) {
        NavHost(
            navController = parentNavController,
            startDestination = BottomTab.CatsList(stringResource(R.string.bottom_tab_title_cats_list))
        ) {
            composable<BottomTab.CatsList> { CatsListScreen() }
            composable<BottomTab.Favourites> { FavouritesScreen() }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    MaterialTheme() {
        HomeScreen()
    }
}
