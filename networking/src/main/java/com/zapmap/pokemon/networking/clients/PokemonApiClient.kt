package com.zapmap.pokemon.networking.clients

import com.zapmap.pokemon.networking.entities.PokemonsResponse

interface PokemonApiClient {
    suspend fun fetchPokemons(
        limit: Int,
        offset: Int
    ): PokemonsResponse
}