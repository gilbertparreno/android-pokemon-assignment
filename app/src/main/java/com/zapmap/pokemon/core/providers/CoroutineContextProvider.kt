package com.zapmap.pokemon.core.providers

import kotlinx.coroutines.Dispatchers

open class CoroutineContextProvider {
    open val Main by lazy { Dispatchers.Main }
    open val IO by lazy { Dispatchers.IO }
}