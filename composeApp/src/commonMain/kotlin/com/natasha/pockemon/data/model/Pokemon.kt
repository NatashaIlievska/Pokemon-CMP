package com.natasha.pockemon.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Pokemon (
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String
)