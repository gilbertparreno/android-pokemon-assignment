package com.zapmap.pokemon.ui.pokemonDetails.factories

import com.zapmap.pokemon.core.extensions.toSentenceCase
import com.zapmap.pokemon.networking.entities.PokemonDetailsResponse
import com.zapmap.pokemon.ui.pokemonDetails.entities.PokemonDetails

object PokemonDetailsFactory {

    fun createPokemonDetailsFromApi(api: PokemonDetailsResponse): PokemonDetails {
        return PokemonDetails(
            name = api.name.toSentenceCase(),
            height = api.height.toString(),
            weight = api.weight.toString(),
            types = api.types.map { it.type.name },
            photo = api.sprites.frontDefault
        )
    }
}