package com.zapmap.pokemon.ui.pokemonList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.zapmap.pokemon.R
import com.zapmap.pokemon.analytics.ZoogleAnalytics
import com.zapmap.pokemon.analytics.ZoogleAnalyticsEvent
import com.zapmap.pokemon.core.base.BaseFragment
import com.zapmap.pokemon.core.entities.TaskStatus.FailureWithException
import com.zapmap.pokemon.core.entities.TaskStatus.SuccessWithResult
import com.zapmap.pokemon.core.helpers.DialogHelper
import com.zapmap.pokemon.databinding.FragmentPokemonListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonListFragment : BaseFragment<FragmentPokemonListBinding, PokemonListViewHandler>(), PokemonListViewHandlerDelegate {

    private val viewModel: PokemonListViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPokemonListBinding
        get() = FragmentPokemonListBinding::inflate

    override fun onCreateView(viewBindingRoot: View) = PokemonListViewHandler()

    override fun onViewCreated(
        viewHelper: PokemonListViewHandler,
        savedInstanceState: Bundle?
    ) {
        viewHelper.delegate = this
        viewHelper.setUpView()
        viewModel.getPokemonList()
    }

    override fun observeChanges() {
        viewModel.pokemonListEvent.observe(this) {
            when (it) {
                is SuccessWithResult -> {
                    fragmentViewHandler.addItems(it.result)
                }
                is FailureWithException -> {
                    if (!fragmentViewHandler.showErrorMessage()) {
                        DialogHelper.showDialog(
                            context = requireContext(),
                            lifecycleOwner = viewLifecycleOwner,
                            dialogTitleRes = R.string.error_no_network_title,
                            dialogMessageRes = R.string.error_no_network_message,
                            positiveButtonTextRes = R.string.retry,
                            negativeButtonTextRes = R.string.dismiss,
                            onPositive = {
                                viewModel.getPokemonList()
                            }
                        )
                    }
                }
                else -> {}
            }
        }
    }

    // MainViewDelegate

    override fun onLoadMore(nextOffset: Int?) {
        viewModel.getPokemonList(nextOffset)
    }

    override fun onOpenPokemonDetails(id: Int) {
        ZoogleAnalytics.logEvent(
            ZoogleAnalyticsEvent(
                "pokemon_selected",
                mapOf("id" to id.toString())
            )
        )
        findNavController().navigate(
            PokemonListFragmentDirections.mainToPokemonDetails(pokemonId = id)
        )
    }
}