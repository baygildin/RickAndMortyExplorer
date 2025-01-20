package com.sbaygildin.rickandmortyexplorer.data.di

import com.sbaygildin.rickandmortyexplorer.data.api.CharacterApi
import com.sbaygildin.rickandmortyexplorer.data.repository.CharacterRepositoryImpl
import com.sbaygildin.rickandmortyexplorer.domain.repository.CharacterRepository
import com.sbaygildin.rickandmortyexplorer.domain.usecase.GetCharacterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCharacterApi(): CharacterApi {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CharacterApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCharacterRepository(api: CharacterApi): CharacterRepository {
        return CharacterRepositoryImpl(api)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object DomainModule {

        @Provides
        @Singleton
        fun provideGetCharacterUseCase(repository: CharacterRepository): GetCharacterUseCase {
            return GetCharacterUseCase(repository)
        }
    }
}