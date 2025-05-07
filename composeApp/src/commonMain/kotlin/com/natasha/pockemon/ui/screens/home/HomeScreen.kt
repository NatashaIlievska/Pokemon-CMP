package com.natasha.pockemon.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.natasha.pockemon.data.model.Pokemon
import com.natasha.pockemon.ui.screens.detail.DetailsScreen
import com.natasha.pockemon.ui.views.CustomTopAppBar
import com.natasha.pockemon.ui.views.UiStateContent
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.resources.stringResource
import pockemon.composeapp.generated.resources.Res
import pockemon.composeapp.generated.resources.app_name


class HomeScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinScreenModel<HomeViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        Scaffold(
            topBar = {
                CustomTopAppBar(
                    title = stringResource(Res.string.app_name)
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

@Composable
fun HomePage(
    content: HomeContent,
    onItemClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = rememberLazyListState(),
        contentPadding = PaddingValues(vertical = 24.dp)
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
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .clickable {
                onItemClicked.invoke(pokemon.id)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        KamelImage(
            resource = {
                asyncPainterResource(pokemon.imageUrl)
            },
            modifier = Modifier.size(80.dp),
            contentDescription = null
        )

        Column(
            modifier = Modifier.fillMaxWidth().padding(start = 8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = pokemon.name,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = pokemon.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}