package com.natasha.pockemon.ui.screens.home

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.natasha.pockemon.data.model.Pokemon
import com.natasha.pockemon.domain.GetPokemonsUseCase
import com.natasha.pockemon.utils.UiState
import com.natasha.pockemon.utils.extensions.toUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val getPokemonsUseCase: GetPokemonsUseCase) : ScreenModel {

    private val _uiState = MutableStateFlow(HomeState(uiState = UiState.Loading))
    val uiState = _uiState.asStateFlow()

    init {
        getPokemons()
    }

    fun getPokemons() {
        screenModelScope.launch {
            _uiState.update { it.copy(uiState = UiState.Loading) }

            val uiState = getPokemonsUseCase().map {
                HomeContent(
                    pokemonList = it
                )
            }.toUIState()

            _uiState.update { it.copy(uiState = uiState) }
        }
    }
}

data class HomeState(val uiState: UiState<HomeContent>)

data class HomeContent(
    val pokemonList: List<Pokemon>,
)


