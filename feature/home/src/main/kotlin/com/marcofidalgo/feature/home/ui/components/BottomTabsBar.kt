package com.marcofidalgo.feature.home.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BottomTabsBar(
    bottomTabs: List<BottomTab>,
    currentBottomTab: BottomTab?,
    onTabClicked: (BottomTab) -> Unit,
) {
    NavigationBar {
        bottomTabs.forEach { bottomTab ->
            NavigationBarItem(
                alwaysShowLabel = true,
                icon = { },
                label = { Text(bottomTab.title) },
                selected = currentBottomTab == bottomTab,
                onClick = { onTabClicked(bottomTab) },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                )
            )
        }
    }
}