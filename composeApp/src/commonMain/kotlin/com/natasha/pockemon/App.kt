package com.natasha.pockemon

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.natasha.pockemon.di.initializeKoin
import com.natasha.pockemon.ui.screens.home.HomeScreen
import com.natasha.pockemon.ui.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    initializeKoin()

    AppTheme {
        Navigator(HomeScreen())
    }
}