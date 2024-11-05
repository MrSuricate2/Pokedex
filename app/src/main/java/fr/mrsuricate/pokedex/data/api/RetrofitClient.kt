package fr.mrsuricate.pokedex.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokemonApi {
    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    //todo barre de recherche
//
//    val trustManager = object : X509TrustManager {
//        override fun getAcceptedIssuers(): Array<X509Certificate>? = arrayOf()
//        override fun checkClientTrusted(certs: Array<X509Certificate>, authType: String) {}
//        override fun checkServerTrusted(certs: Array<X509Certificate>, authType: String) {}
//    }
//
//    val sslContext = SSLContext.getInstance("SSL").apply {
//        init(null, arrayOf(trustManager), java.security.SecureRandom())
//    }
//
//    val client = OkHttpClient.Builder()
//        .sslSocketFactory(sslContext.socketFactory, trustManager)
//        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
//        .client(client)
        .baseUrl(BASE_URL)
        .build()

    val apiService: PokemonApiService by lazy {
        retrofit.create(PokemonApiService::class.java)
    }
}