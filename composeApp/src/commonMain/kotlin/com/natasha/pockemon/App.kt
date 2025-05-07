package com.natasha.pockemon

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.natasha.pockemon.ui.screens.home.HomeScreen
import com.natasha.pockemon.ui.theme.AppTheme

@Composable
fun App() {
    AppTheme {
        Navigator(HomeScreen())
    }
}