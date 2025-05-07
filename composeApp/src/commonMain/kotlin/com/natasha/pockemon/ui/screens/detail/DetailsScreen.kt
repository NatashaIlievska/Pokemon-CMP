package com.natasha.pockemon.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.natasha.pockemon.data.model.Pokemon
import com.natasha.pockemon.ui.theme.AppTheme
import com.natasha.pockemon.ui.views.UiStateContent
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.koin.core.parameter.parametersOf

class DetailsScreen(val id: Int) : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = koinScreenModel<DetailsViewModel>(
            parameters = { parametersOf(id) }
        )
        val uiState by viewModel.uiState.collectAsState()
        AppTheme {
            Scaffold(
                topBar = {
                    uiState.uiState.getSuccessData()?.let { detailsContent ->
                        val pokemon = detailsContent.pokemon
                        TopAppBar(
                            title = { Text(text = pokemon.name) },
                            navigationIcon = {
                                IconButton(onClick = {
                                    navigator.pop()
                                }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Navigate Up"
                                    )
                                }
                            }
                        )
                    }
                }
            ) { paddingValues ->
                UiStateContent(
                    uiState = uiState.uiState,
                    retryCallback = { viewModel.getPokemon(id) }
                ) { content ->
                    DetailsPage(
                        pokemon = content.pokemon,
                        modifier = Modifier.padding(paddingValues)
                    )
                }
            }
        }
    }
}

@Composable
fun DetailsPage(
    pokemon: Pokemon,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 24.dp)
    ) {
        KamelImage(
            resource = {
                asyncPainterResource(pokemon.imageUrl)
            },
            modifier = Modifier.size(30.dp),
            contentDescription = null
        )
        Text(
            text = pokemon.description,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}