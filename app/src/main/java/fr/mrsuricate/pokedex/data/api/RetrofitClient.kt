package fr.mrsuricate.pokedex.data.api

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager

object PokemonApi {
    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    val trustManager = object : X509TrustManager {
        override fun getAcceptedIssuers(): Array<X509Certificate>? = arrayOf()
        override fun checkClientTrusted(certs: Array<X509Certificate>, authType: String) {}
        override fun checkServerTrusted(certs: Array<X509Certificate>, authType: String) {}
    }

    val sslContext = SSLContext.getInstance("SSL").apply {
        init(null, arrayOf(trustManager), java.security.SecureRandom())
    }

    val client = OkHttpClient.Builder()
        .sslSocketFactory(sslContext.socketFactory, trustManager)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .baseUrl(BASE_URL)
        .build()

    val apiService: PokemonApiService by lazy {
        retrofit.create(PokemonApiService::class.java)
    }

    // Centralized function for API call to reduce redundancy
    fun <T> executeApiCall(
        call: Call<T>,
        onSuccess: (Response<T>) -> Unit,
        onFailure: (Throwable) -> Unit = {
            Log.e(
                "RetrofitClient",
                "API Call Failed",
                it
            )
        }
    ) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    onSuccess(response)
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                onFailure(t)
            }
        })
    }
}