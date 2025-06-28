package fr.mrsuricate.pokedex.domain.repository

import android.util.Log
import fr.mrsuricate.pokedex.data.cache.CacheManager

abstract class BaseRepository<T>(
    private val cacheManager: CacheManager
) {
    fun fetchFromCache(url: String, dataType: Class<Array<T>>): List<T>? {
        return try {
            cacheManager.retrieveValue(url, dataType)?.toList()
        } catch (e: Exception) {
            Log.e("BaseRepository", "Cache retrieval error: ${e.message}")
            null
        }
    }

    fun updateCache(url: String, data: Any) {
        try {
            cacheManager.cacheValue(data, url)
            Log.d("BaseRepository", "Cache updated for URL: $url")
        } catch (e: Exception) {
            Log.e("BaseRepository", "Cache update error: ${e.message}")
        }
    }

    abstract suspend fun fetchFromApi(vararg params: Any): List<T>
}