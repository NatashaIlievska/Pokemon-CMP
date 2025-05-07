package com.natasha.pockemon.di

import com.natasha.pockemon.domain.GetPokemonByIdUseCase
import com.natasha.pockemon.domain.GetPokemonsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        GetPokemonsUseCase(
            pokemonRepository = get()
        )
    }

    factory {
        GetPokemonByIdUseCase(
            pokemonRepository = get()
        )
    }
}