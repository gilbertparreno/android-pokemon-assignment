package com.zapmap.pokemon.core.extensions

import com.zapmap.pokemon.core.providers.CoroutineContextProvider
import kotlinx.coroutines.*

fun <T : Any> CoroutineScope.launch(
    coroutineContextProvider: CoroutineContextProvider,
    work: suspend CoroutineScope.() -> T?,
    onSuccess: ((T?) -> Unit),
    onError: ((error: Throwable) -> Unit) = { }
): Job {
    val errorHandler = CoroutineExceptionHandler { _, throwable ->
        CoroutineScope(coroutineContextProvider.Main).launch {
            onError(throwable)
        }
    }
    return launch(coroutineContextProvider.Main + errorHandler) {
        val data = async(coroutineContextProvider.IO) rt@{
            return@rt work()
        }.await()
        onSuccess(data)
    }
}