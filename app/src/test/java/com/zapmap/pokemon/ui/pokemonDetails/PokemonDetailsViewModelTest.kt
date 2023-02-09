package com.zapmap.pokemon.ui.pokemonDetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jraska.livedata.TestObserver
import com.jraska.livedata.test
import com.zapmap.pokemon.core.entities.TaskStatus
import com.zapmap.pokemon.networking.clients.PokemonApiClient
import com.zapmap.pokemon.networking.entities.PokemonDetailsResponse
import com.zapmap.pokemon.ui.pokemonDetails.entities.PokemonDetails
import com.zapmap.pokemon.ui.pokemonDetails.factories.PokemonDetailsFactory
import com.zapmap.pokemon.utils.TestCoroutineRule
import com.zapmap.pokemon.utils.providers.TestCoroutineContextProvider
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PokemonDetailsViewModelTest {

    @get:Rule val instantTestExecutorRule = InstantTaskExecutorRule()
    @get:Rule val testCoroutineRule = TestCoroutineRule()

    @MockK private lateinit var pokemonApiClient: PokemonApiClient

    private lateinit var viewModel: PokemonDetailsViewModel
    private lateinit var testCoroutineContextProvider: TestCoroutineContextProvider
    private lateinit var testPokemonDetailsStatusData: TestObserver<TaskStatus<PokemonDetails>>

    @Before
    fun setUp() {
        mockkObject(PokemonDetailsFactory)
        MockKAnnotations.init(this)
        testCoroutineContextProvider = TestCoroutineContextProvider(testCoroutineRule)
        viewModel = PokemonDetailsViewModel(
            pokemonApiClient,
            testCoroutineContextProvider
        ).apply {
            testPokemonDetailsStatusData = pokemonDetailsTaskStatus.test()
        }
    }

    @Test
    fun `getPokemonDetails(1) - SUCCESS`() = testCoroutineRule.runBlockingTest {
        val testApiResponse = PokemonDetailsResponse(
            name = "",
            height = -1,
            weight = -1,
            sprites = PokemonDetailsResponse.Sprite(""),
            types = emptyList()
        )
        val testApiToLocalEntity = PokemonDetails(
            name = "",
            height = "",
            weight = "",
            photo = "",
            types = emptyList()
        )
        coEvery { pokemonApiClient.fetchPokemonDetails(1) } returns testApiResponse
        coEvery { PokemonDetailsFactory.createPokemonDetailsFromApi(testApiResponse) } returns testApiToLocalEntity
        viewModel.getPokemonDetails(1)
        coVerifyOrder {
            pokemonApiClient.fetchPokemonDetails(1)
            PokemonDetailsFactory.createPokemonDetailsFromApi(testApiResponse)
        }
        testPokemonDetailsStatusData.assertValue(TaskStatus.success(testApiToLocalEntity))
    }

    @Test
    fun `getPokemonList(1) - FAILURE`() = testCoroutineRule.runBlockingTest {
        val error = Error("This is a test exception")
        coEvery { pokemonApiClient.fetchPokemonDetails(1) } throws error
        viewModel.getPokemonDetails(1)
        coVerify { pokemonApiClient.fetchPokemonDetails(1) }
        testPokemonDetailsStatusData.assertValue(TaskStatus.failure(error))
    }
}