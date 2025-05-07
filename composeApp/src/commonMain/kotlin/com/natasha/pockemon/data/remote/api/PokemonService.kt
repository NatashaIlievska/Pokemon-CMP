package com.natasha.pockemon.data.remote.api

import com.natasha.pockemon.data.model.Pokemon
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json


private const val BASE_URL =
    "https://gist.githubusercontent.com/DavidCorrado/8912aa29d7c4a5fbf03993b32916d601/raw/681ef0b793ab444f2d81f04f605037fb44814125/pokemon.json"

class PokemonService(private val httpClient: HttpClient) {

    suspend fun getPokemons(): List<Pokemon> {
        val raw = httpClient.get(BASE_URL).bodyAsText()
        return Json.decodeFromString(raw)
    }
}
