package com.natasha.pockemon.data.repository

import com.natasha.pockemon.data.model.Pokemon

interface PokemonRepository {

    suspend fun getPokemons(): Result<List<Pokemon>>

    suspend fun getPokemon(id: Int): Result<Pokemon>
}