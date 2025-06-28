package fr.mrsuricate.pokedex.ui.koin

import fr.mrsuricate.pokedex.data.api.PokemonApiService
import fr.mrsuricate.pokedex.data.cache.CacheManager
import fr.mrsuricate.pokedex.data.cache.FileManager
import fr.mrsuricate.pokedex.domain.repository.LanguageRepository
import fr.mrsuricate.pokedex.domain.repository.LanguageRepositoryImpl
import fr.mrsuricate.pokedex.domain.repository.PokemonRepository
import fr.mrsuricate.pokedex.domain.repository.PokemonRepositoryImpl
import fr.mrsuricate.pokedex.domain.useCase.LanguageManager
import fr.mrsuricate.pokedex.ui.viewModel.DetailViewModel
import fr.mrsuricate.pokedex.ui.viewModel.HomeViewModel
import fr.mrsuricate.pokedex.ui.viewModel.SettingViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { HomeViewModel(get()) }
    single { SettingViewModel(get()) }
    single { DetailViewModel() }
    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://pokeapi.co/api/v2/")
            .build()
    }
    single {
        get<Retrofit>().create(PokemonApiService::class.java)
    }
    single { LanguageManager(get(), get()) }
    single<PokemonRepository> { PokemonRepositoryImpl(get(), get()) }
    single<LanguageRepository> { LanguageRepositoryImpl(get(), get()) }
    single { CacheManager(get()) }
    single { FileManager(androidContext()) }
}