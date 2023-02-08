package com.zapmap.pokemon.ui.pokemonDetails

import coil.load
import com.zapmap.pokemon.core.base.BaseFragmentView
import com.zapmap.pokemon.databinding.FragmentPokemonDetailsBinding
import com.zapmap.pokemon.ui.pokemonDetails.adapters.PokemonDetailsTypeAdapter
import com.zapmap.pokemon.ui.pokemonDetails.entities.PokemonDetails

interface PokemonDetailsViewDelegate {
    fun onBackPressed()
}

class PokemonDetailsView : BaseFragmentView<FragmentPokemonDetailsBinding>() {

    var delegate: PokemonDetailsViewDelegate? = null

    private val detailsTypeAdapter = PokemonDetailsTypeAdapter()

    override fun setUpView() {
        viewBinding.apply {
            recyclerViewTypes.adapter = detailsTypeAdapter
            toolbar.setNavigationOnClickListener {
                delegate?.onBackPressed()
            }
        }
    }

    fun setUpPokemonDetails(details: PokemonDetails) {
        viewBinding.apply {
            textViewPokemonName.text = details.name
            textViewWeight.text = details.weight
            textViewHeight.text = details.height
            imageViewPokemonFront.load(details.photo)
            detailsTypeAdapter.setListItems(details.types)
        }
    }
}