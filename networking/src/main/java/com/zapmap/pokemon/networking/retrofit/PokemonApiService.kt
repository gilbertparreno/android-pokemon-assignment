package com.zapmap.pokemon.networking.retrofit

import com.zapmap.pokemon.networking.entities.PokemonDetailsResponse
import com.zapmap.pokemon.networking.entities.PokemonsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {

    @GET("pokemon")
    suspend fun fetchPokemons(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonsResponse

    @GET("pokemon/{id}")
    suspend fun fetchPokemonById(
        @Path("id") id: Int,
    ): PokemonDetailsResponse
}