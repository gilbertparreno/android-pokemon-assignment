package com.zapmap.pokemon.ui.pokemonDetails.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.zapmap.pokemon.core.base.BaseRecyclerViewAdapter
import com.zapmap.pokemon.databinding.ViewPokemonDetailsTypeBinding
import com.zapmap.pokemon.ui.pokemonDetails.adapters.callbacks.PokemonDetailsTypeAdapterDiffUtil

class PokemonDetailsTypeAdapter(
    override val items: MutableList<String> = mutableListOf()
) : BaseRecyclerViewAdapter<String>() {

    fun setListItems(newItems: List<String>) {
        val diffResult = DiffUtil.calculateDiff(
            PokemonDetailsTypeAdapterDiffUtil(
                oldItems = items,
                newItems = newItems
            )
        )
        this.items.clear()
        this.items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ViewHolder(
        ViewPokemonDetailsTypeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        viewHolder: ViewHolder,
        item: String,
        position: Int
    ) {
        (viewHolder.viewBinding as ViewPokemonDetailsTypeBinding).textViewType.text = item
    }
}