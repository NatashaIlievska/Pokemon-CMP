package com.natasha.pockemon.data.repository

import com.natasha.pockemon.data.model.Pokemon
import com.natasha.pockemon.data.remote.api.PokemonService
import com.natasha.pockemon.utils.extensions.suspendRunCatching
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class PokemonRepositoryImpl(
    private val service: PokemonService
) : PokemonRepository {

    private val cachedPokemonsFlow = MutableStateFlow<List<Pokemon>?>(null)

    override suspend fun getPokemons(): Result<List<Pokemon>> {
        cachedPokemonsFlow.value?.let { return Result.success(it) }

        return suspendRunCatching {
            val pokemons = service.getPokemons()
            cachedPokemonsFlow.update { pokemons }
            pokemons
        }
    }

    override suspend fun getPokemon(id: Int): Result<Pokemon> = suspendRunCatching {
        val pokemons = getPokemons().getOrThrow()
        pokemons.firstOrNull { it.id == id }
            ?: throw NoSuchElementException("Pokemon with id=$id not found")
    }
}