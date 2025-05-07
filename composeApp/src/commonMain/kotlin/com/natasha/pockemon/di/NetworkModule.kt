package com.natasha.pockemon.di

import com.natasha.pockemon.data.remote.api.PokemonService
import com.natasha.pockemon.data.repository.PokemonRepository
import com.natasha.pockemon.data.repository.PokemonRepositoryImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {

    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    prettyPrint = true
                })
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 5_000
            }
        }
    }

    single { PokemonService(get()) }

    single<PokemonRepository> { PokemonRepositoryImpl(get()) }
}

