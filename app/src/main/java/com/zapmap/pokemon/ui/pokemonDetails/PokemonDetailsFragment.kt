package com.zapmap.pokemon.ui.pokemonDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.zapmap.pokemon.core.base.BaseFragment
import com.zapmap.pokemon.core.entities.TaskStatus.*
import com.zapmap.pokemon.databinding.FragmentPokemonDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailsFragment : BaseFragment<FragmentPokemonDetailsBinding, PokemonDetailsView>(),
    PokemonDetailsViewDelegate {

    private val arguments: PokemonDetailsFragmentArgs by navArgs()
    private val viewModel: PokemonDetailsViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPokemonDetailsBinding
        get() = FragmentPokemonDetailsBinding::inflate

    override fun onCreateView(viewBindingRoot: View) = PokemonDetailsView()

    override fun onViewCreated(
        viewHelper: PokemonDetailsView,
        savedInstanceState: Bundle?
    ) {
        viewHelper.delegate = this
        viewHelper.setUpView()
        viewModel.getPokemonDetails(arguments.pokemonId)
    }

    override fun observeChanges() {
        viewModel.pokemonDetailsTaskStatus.observe(this) {
            when (it) {
                is Loading -> {}
                is SuccessWithResult -> {
                    rootView.setUpPokemonDetails(it.result)
                }
                is FailureWithException -> {
                    Toast.makeText(
                        requireContext(),
                        it.error.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {}
            }
        }
    }

    // PokemonDetailsViewDelegate

    override fun onBackPressed() {
        findNavController().popBackStack()
    }
}