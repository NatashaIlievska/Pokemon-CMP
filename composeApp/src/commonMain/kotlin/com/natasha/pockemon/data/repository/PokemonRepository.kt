package com.natasha.pockemon.data.repository

import com.natasha.pockemon.data.model.Pokemon

/**
 * Repository for accessing Pokemon data from remote and local sources
 */
interface PokemonRepository {

    /**
     * Fetches the full list of Pokémon.
     *
     * @return [Result] containing a list of [Pokemon] on success,
     * or an exception if the operation fails.
     */
    suspend fun getPokemons(): Result<List<Pokemon>>

    /**
     * Fetches a single [Pokemon] by its unique identifier.
     *
     * @param id The unique ID of the Pokémon to retrieve.
     * @return [Result] containing the [Pokemon] if found,
     * or an exception if not found or the operation fails.
     */
    suspend fun getPokemon(id: Int): Result<Pokemon>
}