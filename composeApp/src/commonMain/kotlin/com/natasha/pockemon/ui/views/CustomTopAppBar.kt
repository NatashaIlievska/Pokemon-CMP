package com.natasha.pockemon.ui.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.resources.stringResource
import pockemon.composeapp.generated.resources.Res
import pockemon.composeapp.generated.resources.navigate_up

@Composable
fun CustomTopAppBar(
    title: String,
    showUpButton: Boolean = false,
) {
    val navigator = LocalNavigator.currentOrThrow
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            if (showUpButton) {
                IconButton(onClick = {
                    navigator.pop()
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(Res.string.navigate_up)
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}