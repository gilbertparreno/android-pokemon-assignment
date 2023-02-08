package com.zapmap.pokemon.ui.pokemonList.adapters.callbacks

import androidx.recyclerview.widget.DiffUtil
import com.zapmap.pokemon.ui.pokemonList.entities.PokemonListItem

class PokemonListAdapterDiffUtil(
    val oldItems: List<PokemonListItem>,
    val newItems: List<PokemonListItem>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }
}