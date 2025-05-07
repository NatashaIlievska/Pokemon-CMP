package com.natasha.pockemon.domain

import com.natasha.pockemon.data.model.Pokemon
import com.natasha.pockemon.data.repository.PokemonRepository
import com.natasha.pockemon.utils.extensions.suspendRunCatching

class GetPokemonByIdUseCase(private val pokemonRepository: PokemonRepository) {

    suspend operator fun invoke(id: Int): Result<Pokemon> = suspendRunCatching {
        val pokemons = pokemonRepository.getPokemons().getOrThrow()
        pokemons.firstOrNull { it.id == id }
            ?: throw NoSuchElementException("Pokemon with id=$id not found")
    }
}