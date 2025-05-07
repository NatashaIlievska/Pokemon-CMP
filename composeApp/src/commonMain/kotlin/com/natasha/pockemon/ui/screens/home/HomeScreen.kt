package com.natasha.pockemon.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.natasha.pockemon.data.model.Pokemon
import com.natasha.pockemon.ui.screens.detail.DetailsScreen
import com.natasha.pockemon.ui.theme.AppTheme
import com.natasha.pockemon.ui.views.UiStateContent
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@OptIn(ExperimentalMaterial3Api::class)
class HomeScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinScreenModel<HomeViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        AppTheme {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("My App") }
                    )
                }
            ) { paddingValues ->
                val navigator = LocalNavigator.currentOrThrow
                UiStateContent(
                    uiState = uiState.uiState,
                    retryCallback = { viewModel.getPokemons() }
                ) { content ->
                    HomePage(
                        content = content,
                        onItemClicked = { id ->
                            navigator.push(DetailsScreen(id))
                        },
                        modifier = Modifier.padding(paddingValues)
                    )
                }
            }
        }
    }
}

@Composable
fun HomePage(
    content: HomeContent,
    onItemClicked: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        state = rememberLazyListState()
    ) {
        itemsIndexed(content.pokemonList) { index, pokemon ->
            HomeItemList(
                pokemon = pokemon,
                onItemClicked = onItemClicked,
                modifier = Modifier.padding(bottom = if (index == content.pokemonList.size - 1) 0.dp else 20.dp)
            )
        }
    }
}

@Composable
fun HomeItemList(
    pokemon: Pokemon,
    onItemClicked: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier.clickable {
        onItemClicked.invoke(pokemon.id)
    }) {
        KamelImage(
            resource = {
                asyncPainterResource(pokemon.imageUrl)
            },
            modifier = Modifier.size(30.dp),
            contentDescription = null
        )

        Column(
            modifier = Modifier.fillMaxWidth().padding(start = 8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = pokemon.name,
                style = MaterialTheme.typography.labelLarge
            )

            Text(
                text = pokemon.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}