package com.zapmap.pokemon.ui.pokemonList.entities

import com.zapmap.pokemon.core.extensions.allTrue
import java.util.*

sealed class PokemonListItem {
    data class LoadingMoreItem(val nextOffset: Int) : PokemonListItem() {
        override fun equals(other: Any?): Boolean {
            if (other !is LoadingMoreItem) return false
            return nextOffset == other.nextOffset
        }

        override fun hashCode() = Objects.hashCode(nextOffset)
    }

    data class RetryItem(val nextOffset: Int) : PokemonListItem() {
        override fun equals(other: Any?): Boolean {
            if (other !is RetryItem) return false
            return nextOffset == other.nextOffset
        }

        override fun hashCode() = Objects.hashCode(nextOffset)
    }

    data class PokemonItem(
        val name: String,
        val id: Int
    ) : PokemonListItem() {
        override fun equals(other: Any?): Boolean {
            if (other !is PokemonItem) return false
            return allTrue(
                name == other.name,
                id == other.id
            )
        }

        override fun hashCode() = Objects.hash(name, id)
    }
}