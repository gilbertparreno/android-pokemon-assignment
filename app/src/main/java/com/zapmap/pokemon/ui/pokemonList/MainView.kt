package com.zapmap.pokemon.ui.pokemonList

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zapmap.pokemon.core.base.BaseFragmentView
import com.zapmap.pokemon.databinding.FragmentMainBinding
import com.zapmap.pokemon.ui.pokemonList.adapters.PokemonListAdapter
import com.zapmap.pokemon.ui.pokemonList.entities.PokemonListItem
import com.zapmap.pokemon.ui.pokemonList.entities.PokemonListItem.*

interface MainViewDelegate {
    fun onLoadMore(nextOffset: Int?)
    fun onOpenPokemonDetails(id: Int)
}

class MainView : BaseFragmentView<FragmentMainBinding>() {

    var delegate: MainViewDelegate? = null

    private val pokemonAdapter = PokemonListAdapter(
        onRetry = { nextOffset ->
            delegate?.onLoadMore(nextOffset)
        },
        onItemClicked = { id ->
            delegate?.onOpenPokemonDetails(id)
        }
    )

    override fun setUpView() {
        viewBinding.apply {
            setUpRecyclerView(recyclerViewPokemon)
        }
    }

    fun addItems(items: List<PokemonListItem>) {
        val mergedItems = pokemonAdapter.items.filterIsInstance<PokemonItem>()
            .plus(items)
        pokemonAdapter.setListItems(mergedItems)
    }

    fun showErrorMessage() = pokemonAdapter.replaceLoadMoreItemWithRetryItem()

    private fun setUpRecyclerView(recyclerView: RecyclerView) {
        with(recyclerView) {
            adapter = pokemonAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(
                    recyclerView: RecyclerView,
                    dx: Int,
                    dy: Int
                ) {
                    super.onScrolled(recyclerView, dx, dy)
                    val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager)
                        .findLastCompletelyVisibleItemPosition()
                    if (dy > 0 && lastVisibleItemPosition != RecyclerView.NO_POSITION) {
                        (pokemonAdapter.items[lastVisibleItemPosition] as? LoadingMoreItem)?.let {
                            delegate?.onLoadMore(it.nextOffset)
                        }
                    }
                }
            })
        }
    }
}