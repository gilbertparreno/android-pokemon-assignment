package com.zapmap.pokemon.ui.pokemonDetails.adapters.callbacks

import androidx.recyclerview.widget.DiffUtil

class PokemonDetailsTypeAdapterDiffUtil(
    val oldItems: List<String>,
    val newItems: List<String>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ) = oldItems[oldItemPosition] == newItems[newItemPosition]

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ) = oldItems[oldItemPosition] == newItems[newItemPosition]
}