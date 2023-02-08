package com.zapmap.pokemon.ui.pokemonList.factories

import com.zapmap.pokemon.networking.BuildConfig
import com.zapmap.pokemon.networking.entities.PokemonsResponse
import com.zapmap.pokemon.ui.pokemonList.entities.PokemonListItem
import com.zapmap.pokemon.ui.pokemonList.entities.PokemonListItem.LoadingMoreItem
import com.zapmap.pokemon.ui.pokemonList.entities.PokemonListItem.PokemonItem

object PokemonListFactory {

    fun createPokemonListItemsFromApi(
        apiResponse: PokemonsResponse
    ): List<PokemonListItem> {
        val items = mutableListOf<PokemonListItem>()
        items.addAll(
            apiResponse.pokemonItems.map {
                val trimmedUrl = it.url.replace(
                    BuildConfig.POKEMON_API_URI.plus("pokemon/"),
                    ""
                )
                val id = trimmedUrl.substring(0, trimmedUrl.length - 1).toInt()
                PokemonItem(
                    name = it.name,
                    id = id
                )
            }
        )
        apiResponse.getNextOffset()?.let {
            items.add(LoadingMoreItem(it))
        }
        return items
    }
}