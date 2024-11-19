package fr.mrsuricate.pokedex.ui.module

import fr.mrsuricate.pokedex.data.api.PokemonApiService
import fr.mrsuricate.pokedex.domain.repository.LanguageRepository
import fr.mrsuricate.pokedex.domain.repository.LanguageRepositoryImpl
import fr.mrsuricate.pokedex.domain.repository.PokemonRepository
import fr.mrsuricate.pokedex.domain.repository.PokemonRepositoryImpl
import fr.mrsuricate.pokedex.domain.useCase.LanguageSelected
import fr.mrsuricate.pokedex.ui.viewModel.DetailViewModel
import fr.mrsuricate.pokedex.ui.viewModel.HomeViewModel
import fr.mrsuricate.pokedex.ui.viewModel.SettingViewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { HomeViewModel(get()) }
    single { SettingViewModel(get(), get()) }
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
    single { LanguageSelected }
    single<PokemonRepository> { PokemonRepositoryImpl(get()) }
    single<LanguageRepository> { LanguageRepositoryImpl(get(), get()) }
}