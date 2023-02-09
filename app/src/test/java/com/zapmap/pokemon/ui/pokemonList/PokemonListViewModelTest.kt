package com.zapmap.pokemon.ui.pokemonList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jraska.livedata.TestObserver
import com.jraska.livedata.test
import com.zapmap.pokemon.core.entities.TaskStatus
import com.zapmap.pokemon.networking.clients.PokemonApiClient
import com.zapmap.pokemon.networking.entities.PokemonsResponse
import com.zapmap.pokemon.ui.pokemonList.entities.PokemonListItem
import com.zapmap.pokemon.ui.pokemonList.factories.PokemonListFactory
import com.zapmap.pokemon.utils.TestCoroutineRule
import com.zapmap.pokemon.utils.providers.TestCoroutineContextProvider
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PokemonListViewModelTest {

    @get:Rule val instantTestExecutorRule = InstantTaskExecutorRule()
    @get:Rule val testCoroutineRule = TestCoroutineRule()

    @MockK private lateinit var pokemonApiClient: PokemonApiClient

    private lateinit var viewModel: PokemonListViewModel
    private lateinit var testCoroutineContextProvider: TestCoroutineContextProvider
    private lateinit var testMainListStatusData: TestObserver<TaskStatus<List<PokemonListItem>>>

    @Before
    fun setUp() {
        mockkObject(PokemonListFactory)
        MockKAnnotations.init(this)
        testCoroutineContextProvider = TestCoroutineContextProvider(testCoroutineRule)
        viewModel = PokemonListViewModel(
            pokemonApiClient,
            testCoroutineContextProvider
        ).apply {
            testMainListStatusData = pokemonListEvent.test()
        }
    }

    @Test
    fun `getPokemonList(0) - SUCCESS`() = testCoroutineRule.runBlockingTest {
        val testApiResponse = PokemonsResponse(count = 0, next = null, listOf())
        val testApiToLocalEntity = listOf(PokemonListItem.PokemonItem(name = "pickachu", id = 1))
        coEvery { pokemonApiClient.fetchPokemons(50, 0) } returns testApiResponse
        coEvery { PokemonListFactory.createPokemonListItemsFromApi(testApiResponse) } returns testApiToLocalEntity
        viewModel.getPokemonList(0)
        coVerifyOrder {
            pokemonApiClient.fetchPokemons(50, 0)
            PokemonListFactory.createPokemonListItemsFromApi(testApiResponse)
        }
        testMainListStatusData.assertValue(TaskStatus.success(testApiToLocalEntity))
    }

    @Test
    fun `getPokemonList(0) - FAILURE`() = testCoroutineRule.runBlockingTest {
        val error = Error("This is a test exception")
        coEvery { pokemonApiClient.fetchPokemons(50, 0) } throws error
        viewModel.getPokemonList(0)
        coVerify { pokemonApiClient.fetchPokemons(50, 0) }
        testMainListStatusData.assertValue(TaskStatus.failure(error))
    }
}