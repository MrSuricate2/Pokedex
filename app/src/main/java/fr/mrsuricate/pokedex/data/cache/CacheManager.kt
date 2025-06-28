package fr.mrsuricate.pokedex.data.cache

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

class CacheManager(private val fileManager: FileManager) {

    private fun getFileNameFromUrl(url: String): String {
        return when {
            url == "https://pokeapi.co/api/v2/pokemon" -> "pokemon.txt"
            url.startsWith("https://pokeapi.co/api/v2/pokemon/") -> {
                val pokemon = url.removePrefix("https://pokeapi.co/api/v2/pokemon/")
                "pokemon_$pokemon.txt"
            }
            url == "https://pokeapi.co/api/v2/language" -> "language.txt"
            url == "locale" -> "selected_language.txt"
            else -> throw IllegalArgumentException("URL invalid: $url")
        }
    }

    fun <T> cacheValue(data: T, url: String): Boolean {
        return try {
            val fileName = getFileNameFromUrl(url)
            val jsonData = Gson().toJson(data)
            fileManager.saveData(jsonData, fileName)
            Log.d("CacheManager", "Successfully cached data for $url")
            true
        } catch (e: Exception) {
            Log.e("CacheManager", "Error caching data for $url", e)
            false
        }
    }

    fun <T> retrieveValue(url: String, data: Class<T>): T? {
        return try {
            val fileName = getFileNameFromUrl(url)

            // Check if file exists first
            if (!fileManager.fileExists(fileName)) {
                Log.d("CacheManager", "Cache file does not exist for $url")
                return null
            }

            val jsonData = fileManager.loadData(fileName)
            if (jsonData.isNullOrEmpty()) {
                Log.d("CacheManager", "Cache file is empty for $url")
                return null
            }

            val result = Gson().fromJson(jsonData, data)
            Log.d("CacheManager", "Successfully retrieved cached data for $url")
            result
        } catch (e: JsonSyntaxException) {
            Log.e("CacheManager", "Invalid JSON in cache for $url", e)
            // Delete corrupted cache file
            try {
                val fileName = getFileNameFromUrl(url)
                fileManager.deleteFile(fileName)
                Log.d("CacheManager", "Deleted corrupted cache file for $url")
            } catch (deleteError: Exception) {
                Log.e("CacheManager", "Error deleting corrupted cache file", deleteError)
            }
            null
        } catch (e: Exception) {
            Log.e("CacheManager", "Error retrieving cached data for $url", e)
            null
        }
    }

    fun clearCache(url: String): Boolean {
        return try {
            val fileName = getFileNameFromUrl(url)
            val deleted = fileManager.deleteFile(fileName)
            if (deleted) {
                Log.d("CacheManager", "Successfully cleared cache for $url")
            } else {
                Log.d("CacheManager", "Cache file did not exist for $url")
            }
            deleted
        } catch (e: Exception) {
            Log.e("CacheManager", "Error clearing cache for $url", e)
            false
        }
    }

    fun isCacheAvailable(url: String): Boolean {
        return try {
            val fileName = getFileNameFromUrl(url)
            fileManager.fileExists(fileName)
        } catch (e: Exception) {
            false
        }
    }

}
