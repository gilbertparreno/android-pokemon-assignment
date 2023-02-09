package com.zapmap.pokemon.ui.pokemonList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zapmap.pokemon.core.entities.TaskStatus
import com.zapmap.pokemon.core.extensions.SingleLiveEvent
import com.zapmap.pokemon.core.extensions.launch
import com.zapmap.pokemon.core.providers.CoroutineContextProvider
import com.zapmap.pokemon.networking.clients.PokemonApiClient
import com.zapmap.pokemon.ui.pokemonList.entities.PokemonListItem
import com.zapmap.pokemon.ui.pokemonList.factories.PokemonListFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonApiClient: PokemonApiClient,
    private val coroutineContextProvider: CoroutineContextProvider
) : ViewModel() {

    companion object {
        private const val LIMIT = 50
    }

    val pokemonListEvent = SingleLiveEvent<TaskStatus<List<PokemonListItem>>>()
    private var activeGetPokemonJob: Job? = null

    fun getPokemonList(offset: Int? = null) {
        if (activeGetPokemonJob != null) return
        viewModelScope.launch(
            coroutineContextProvider = coroutineContextProvider,
            work = {
                pokemonApiClient.fetchPokemons(limit = LIMIT, offset = offset ?: 0)
            },
            onSuccess = {
                it?.let {
                    pokemonListEvent.value = TaskStatus.success(
                        PokemonListFactory.createPokemonListItemsFromApi(it)
                    )
                }
                activeGetPokemonJob = null
            },
            onError = {
                pokemonListEvent.value = TaskStatus.failure(it)
                activeGetPokemonJob = null
            }
        ).also {
            activeGetPokemonJob = it
        }
    }
}