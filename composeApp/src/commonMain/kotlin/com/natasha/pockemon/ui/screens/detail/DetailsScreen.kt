package com.natasha.pockemon.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.natasha.pockemon.data.model.Pokemon
import com.natasha.pockemon.ui.views.CustomTopAppBar
import com.natasha.pockemon.ui.views.UiStateContent
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.koin.core.parameter.parametersOf

class DetailsScreen(val id: Int) : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinScreenModel<DetailsViewModel>(
            parameters = { parametersOf(id) }
        )
        val uiState by viewModel.uiState.collectAsState()

        Scaffold(
            topBar = {
                DetailsTopBar(uiState = uiState)
            }
        ) { paddingValues ->
            UiStateContent(
                uiState = uiState.uiState,
                retryCallback = { viewModel.fetchPokemon(id) }
            ) { content ->
                DetailsPage(
                    pokemon = content.pokemon,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}

@Composable
private fun DetailsTopBar(uiState: DetailsState) {
    uiState.uiState.getSuccessData()?.let { detailsContent ->
        CustomTopAppBar(
            title = detailsContent.pokemon.name,
            // https://github.com/adrielcafe/voyager/issues/144 - uncomment below line when this issue is fix
            // showUpButton = getPlatform().type == PlatformType.ANDROID
            showUpButton = true
        )
    }
}

@Composable
private fun DetailsPage(
    pokemon: Pokemon,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        KamelImage(
            resource = {
                asyncPainterResource(pokemon.imageUrl)
            },
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.FillBounds,
            contentDescription = null
        )
        Text(
            text = pokemon.description,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}