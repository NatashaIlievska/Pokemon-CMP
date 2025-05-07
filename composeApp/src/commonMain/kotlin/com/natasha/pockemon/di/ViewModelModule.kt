package com.natasha.pockemon.di

import com.natasha.pockemon.ui.screens.detail.DetailsViewModel
import com.natasha.pockemon.ui.screens.home.HomeViewModel
import io.ktor.http.parameters
import org.koin.dsl.module

val viewModelModule = module {

    factory {
        HomeViewModel(get())
    }

    factory { (id: Int) ->
        DetailsViewModel(id = id, getPokemonByIdUseCase = get())
    }
}