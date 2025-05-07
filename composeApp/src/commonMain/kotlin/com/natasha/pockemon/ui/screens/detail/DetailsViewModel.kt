package com.natasha.pockemon.ui.screens.detail

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.natasha.pockemon.data.model.Pokemon
import com.natasha.pockemon.domain.GetPokemonByIdUseCase
import com.natasha.pockemon.ui.screens.home.HomeContent
import com.natasha.pockemon.utils.UiState
import com.natasha.pockemon.utils.extensions.toUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailsViewModel(
    id: Int,
    private val getPokemonByIdUseCase: GetPokemonByIdUseCase,
) : ScreenModel {

    private val _uiState = MutableStateFlow(DetalsState(uiState = UiState.Loading))
    val uiState = _uiState.asStateFlow()

    init {
        getPokemon(id)
    }

    fun getPokemon(id: Int) {
        screenModelScope.launch {
            _uiState.update { it.copy(uiState = UiState.Loading) }

            val uiState = getPokemonByIdUseCase(id).map {
                DetailsContent(pokemon = it)
            }.toUIState()

            _uiState.update { it.copy(uiState = uiState) }
        }
    }
}

data class DetalsState(val uiState: UiState<DetailsContent>)

data class DetailsContent(
    val pokemon: Pokemon,
)