package com.zapmap.pokemon.networking.retrofit

import com.zapmap.pokemon.networking.entities.PokemonsResponse
import com.zapmap.pokemon.networking.entities.RemotePokemonItem
import retrofit2.Response
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
    ): Response<RemotePokemonItem>
}