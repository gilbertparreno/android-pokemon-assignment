package com.zapmap.pokemon.ui.pokemonDetails.entities

data class PokemonDetails(
    val name: String,
    val height: String,
    val weight: String,
    val photo: String,
    val types: List<String>
)