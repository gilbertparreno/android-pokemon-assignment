package com.zapmap.pokemon.core.extensions

fun String.toSentenceCase(): String {
    return lowercase().replaceFirstChar(Char::uppercase)
}