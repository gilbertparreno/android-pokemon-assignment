package com.zapmap.pokemon.ui.pokemonList.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.zapmap.pokemon.R
import com.zapmap.pokemon.core.base.BaseRecyclerViewAdapter
import com.zapmap.pokemon.core.extensions.runDelayed
import com.zapmap.pokemon.core.extensions.setDebounceClickListener
import com.zapmap.pokemon.databinding.ListItemPokemonBinding
import com.zapmap.pokemon.databinding.ViewPokemonListLoadingMoreItemBinding
import com.zapmap.pokemon.databinding.ViewPokemonListRetryBinding
import com.zapmap.pokemon.ui.pokemonList.adapters.callbacks.PokemonListAdapterDiffUtil
import com.zapmap.pokemon.ui.pokemonList.entities.PokemonListItem
import com.zapmap.pokemon.ui.pokemonList.entities.PokemonListItem.*

class PokemonListAdapter(
    override val items: MutableList<PokemonListItem> = mutableListOf(),
    private val onRetry: ((nextOffset: Int) -> Unit)? = null,
    private val onItemClicked: ((id: Int) -> Unit)? = null
) : BaseRecyclerViewAdapter<PokemonListItem>(items) {

    fun setListItems(newItems: List<PokemonListItem>) {
        val diffResult = DiffUtil.calculateDiff(
            PokemonListAdapterDiffUtil(
                oldItems = items,
                newItems = newItems
            )
        )
        this.items.clear()
        this.items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    fun replaceLoadMoreItemWithRetryItem(): Boolean {
        val nextOffset = (items.lastOrNull() as? LoadingMoreItem)?.nextOffset
            ?: return false
        setListItems(
            items.filterIsInstance<PokemonItem>()
                .plus(RetryItem(nextOffset))
        )
        return true
    }

    private fun replaceRetryWithLoadMoreItem(): Boolean {
        val nextOffset = (items.lastOrNull() as? RetryItem)?.nextOffset
            ?: return false
        setListItems(
            items.filterIsInstance<PokemonItem>()
                .plus(LoadingMoreItem(nextOffset))
        )
        return true
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            when (viewType) {
                R.layout.view_pokemon_list_loading_more_item -> ViewPokemonListLoadingMoreItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                R.layout.view_pokemon_list_retry -> ViewPokemonListRetryBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                else -> ListItemPokemonBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            }
        )
    }

    override fun onBindViewHolder(
        viewHolder: ViewHolder,
        item: PokemonListItem,
        position: Int
    ) {
        when (item) {
            is RetryItem -> {
                viewHolder.viewBinding.root.setDebounceClickListener {
                    runDelayed(300) {
                        onRetry?.invoke(item.nextOffset)
                    }
                    replaceRetryWithLoadMoreItem()
                }
            }
            is PokemonItem -> {
                with(viewHolder.viewBinding as ListItemPokemonBinding) {
                    textViewName.text = item.name
                    root.setDebounceClickListener {
                        onItemClicked?.invoke(item.id)
                    }
                }
            }
            else -> {}
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is LoadingMoreItem -> R.layout.view_pokemon_list_loading_more_item
            is RetryItem -> R.layout.view_pokemon_list_retry
            else -> R.layout.list_item_pokemon
        }
    }
}