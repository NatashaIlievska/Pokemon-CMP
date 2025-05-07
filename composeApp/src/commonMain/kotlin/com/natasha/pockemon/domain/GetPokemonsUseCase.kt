package com.natasha.pockemon.domain

import com.natasha.pockemon.data.model.Pokemon
import com.natasha.pockemon.data.repository.PokemonRepository
import com.natasha.pockemon.utils.extensions.suspendRunCatching

class GetPokemonsUseCase(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(): Result<List<Pokemon>> = suspendRunCatching {
       return pokemonRepository.getPokemons()
    }
}
