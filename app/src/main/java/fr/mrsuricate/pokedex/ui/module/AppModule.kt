package fr.mrsuricate.pokedex.ui.module

import fr.mrsuricate.pokedex.data.api.PokemonApiService
import fr.mrsuricate.pokedex.domain.repository.PokemonRepository
import fr.mrsuricate.pokedex.domain.repository.PokemonRepositoryImpl
import fr.mrsuricate.pokedex.ui.viewModel.DetailViewModel
import fr.mrsuricate.pokedex.ui.viewModel.HomeViewModel
import fr.mrsuricate.pokedex.ui.viewModel.SettingViewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { HomeViewModel(get()) }
    single { SettingViewModel() }
    single { DetailViewModel() }
    single {
//        val trustManager = object : X509TrustManager {
//            override fun getAcceptedIssuers(): Array<X509Certificate>? = arrayOf()
//            override fun checkClientTrusted(certs: Array<X509Certificate>, authType: String) {}
//            override fun checkServerTrusted(certs: Array<X509Certificate>, authType: String) {}
//        }
//
//        val sslContext = SSLContext.getInstance("SSL").apply {
//            init(null, arrayOf(trustManager), java.security.SecureRandom())
//        }
//
//        val client = OkHttpClient.Builder()
//            .sslSocketFactory(sslContext.socketFactory, trustManager)
//            .build()

        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
            .baseUrl("https://pokeapi.co/api/v2/")
            .build()
    }
    single {
        get<Retrofit>().create(PokemonApiService::class.java)
    }
    single<PokemonRepository> { PokemonRepositoryImpl(get()) }
}