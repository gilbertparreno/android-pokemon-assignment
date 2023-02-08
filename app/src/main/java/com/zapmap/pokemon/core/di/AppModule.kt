package com.zapmap.pokemon.core.di

import com.zapmap.pokemon.core.providers.CoroutineContextProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class AppModule {

    @Provides
    @ViewModelScoped
    fun providesCoroutineContextProvider(): CoroutineContextProvider {
        return CoroutineContextProvider()
    }
}