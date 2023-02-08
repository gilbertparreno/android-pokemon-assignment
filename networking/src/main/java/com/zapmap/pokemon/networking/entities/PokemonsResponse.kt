package com.zapmap.pokemon.networking.entities

import android.net.Uri
import com.squareup.moshi.Json

data class PokemonsResponse(
    val count: Int,
    val next: String? = null, // if this is null we've reached the end of the list
    @Json(name = "results")
    val pokemonItems: List<RemotePokemonItem>
) {

    fun getNextOffset(): Int? {
        return next?.let { Uri.parse(it) }
            ?.getQueryParameter("offset")
            ?.toInt()
    }
}