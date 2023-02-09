package com.zapmap.pokemon.utils.providers

import com.zapmap.pokemon.core.providers.CoroutineContextProvider
import com.zapmap.pokemon.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
class TestCoroutineContextProvider(
    testCoroutineRule: TestCoroutineRule
) : CoroutineContextProvider() {
    override var Main: CoroutineContext = testCoroutineRule.testCoroutineDispatcher
    override var IO: CoroutineContext = testCoroutineRule.testCoroutineDispatcher
}