package com.zapmap.pokemon.networking.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemotePokemonItem(
    val name: String,
    val url: String
)