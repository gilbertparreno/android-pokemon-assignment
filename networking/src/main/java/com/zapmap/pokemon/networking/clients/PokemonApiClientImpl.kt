package com.zapmap.pokemon.networking.clients

import com.zapmap.pokemon.networking.entities.PokemonDetailsResponse
import com.zapmap.pokemon.networking.entities.PokemonsResponse
import com.zapmap.pokemon.networking.retrofit.PokemonApiService
import javax.inject.Inject

class PokemonApiClientImpl @Inject constructor(
    private val pokemonApiService: PokemonApiService
) : PokemonApiClient {

    override suspend fun fetchPokemons(
        limit: Int,
        offset: Int
    ): PokemonsResponse {
        return pokemonApiService.fetchPokemons(
            limit,
            offset
        )
    }

    override suspend fun fetchPokemonDetails(pokemonId: Int): PokemonDetailsResponse {
        return pokemonApiService.fetchPokemonById(pokemonId)
    }
}