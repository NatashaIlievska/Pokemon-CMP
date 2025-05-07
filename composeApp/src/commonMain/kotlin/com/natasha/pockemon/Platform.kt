package com.natasha.pockemon

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform