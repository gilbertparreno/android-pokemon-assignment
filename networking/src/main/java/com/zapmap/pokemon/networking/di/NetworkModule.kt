package com.zapmap.pokemon.networking.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.zapmap.pokemon.networking.BuildConfig
import com.zapmap.pokemon.networking.clients.PokemonApiClient
import com.zapmap.pokemon.networking.clients.PokemonApiClientImpl
import com.zapmap.pokemon.networking.retrofit.PokemonApiService
import dagger.Module
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesPokemonApiClient(pokemonApiService: PokemonApiService): PokemonApiClient {
        return PokemonApiClientImpl(pokemonApiService)
    }

    @Provides
    @Singleton
    fun providesPokemonApiService(retrofit: Retrofit): PokemonApiService {
        return retrofit.create(PokemonApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesRetrofit(
        moshi: Moshi,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.POKEMON_API_URI)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) BODY else NONE
        }
        return OkHttpClient.Builder()
            .apply {
                addInterceptor(loggingInterceptor)
            }.build()
    }
}