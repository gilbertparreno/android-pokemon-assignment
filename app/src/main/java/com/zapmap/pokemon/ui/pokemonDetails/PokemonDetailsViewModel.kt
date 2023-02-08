package com.zapmap.pokemon.ui.pokemonDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zapmap.pokemon.core.entities.TaskStatus
import com.zapmap.pokemon.core.extensions.SingleLiveEvent
import com.zapmap.pokemon.core.extensions.launch
import com.zapmap.pokemon.core.providers.CoroutineContextProvider
import com.zapmap.pokemon.networking.clients.PokemonApiClient
import com.zapmap.pokemon.ui.pokemonDetails.entities.PokemonDetails
import com.zapmap.pokemon.ui.pokemonDetails.factories.PokemonDetailsFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val pokemonApiClient: PokemonApiClient,
    private val coroutineContextProvider: CoroutineContextProvider
) : ViewModel() {

    val pokemonDetailsTaskStatus = SingleLiveEvent<TaskStatus<PokemonDetails>>()

    fun getPokemonDetails(pokemonId: Int) {
        viewModelScope.launch(
            coroutineContextProvider = coroutineContextProvider,
            work = {
                pokemonApiClient.fetchPokemonDetails(pokemonId)
            },
            onSuccess = {
                pokemonDetailsTaskStatus.value = TaskStatus.success(PokemonDetailsFactory.createPokemonDetailsFromApi(it!!))
            },
            onError = {
                pokemonDetailsTaskStatus.value = TaskStatus.failure(it)
            }
        )
    }
}