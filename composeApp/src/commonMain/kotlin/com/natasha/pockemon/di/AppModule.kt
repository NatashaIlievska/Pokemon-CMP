package com.natasha.pockemon.di

import org.koin.core.context.startKoin

val appModules = networkModule + viewModelModule + useCaseModule

fun initializeKoin() {
    startKoin {
        modules(appModules)
    }
}