package fr.mrsuricate.pokedex.data.cache

import com.google.gson.Gson
import fr.mrsuricate.pokedex.data.api.model.NamedApiResource
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class CacheManagerTest {

    // Stocker des requetes en cache:
    //   - Prendre un objet en input, associé à une URL et sérialiser l'objet dans un fichier
    // Récupérer un objet s'il a déjà été sauvegardé
    //   - Prendre une URL en argument, et changer l'objet depuis un fichier s'il existe
    //   - Si l'objet n'existe pas, on renvoie null

    @Test
    fun `should create file`() {
        // Given
        val fileManager: FileManager = mock { }
        val cacheManager = CacheManager(fileManager)
        val fileName: String = "https://pokeapi.co/api/v2/pokemon"

        // When
        cacheManager.cacheValue(Any(), fileName)

        // Then
        verify(fileManager).saveData(any(), eq("pokemons.txt"))
    }

    @Test
    fun `should create file with specific URL`() {
        // Given
        val fileManager: FileManager = mock { }
        val cacheManager = CacheManager(fileManager)
        val data: Any = "data"
        val fileName: String = "https://pokeapi.co/api/v2/language"

        // When
        cacheManager.cacheValue(Any(), fileName)

        // Then
        verify(fileManager).saveData(any(), eq("language.txt"))
    }
    @Test
    fun `should create file with serialize objet`() {
        // Given
        val fileManager: FileManager = mock { }
        val cacheManager = CacheManager(fileManager)
        val gson = Gson()
        val data = gson.toJson(NamedApiResource())

        // When
        cacheManager.cacheValue(data, any())

        // Then
        verify(fileManager).saveData("{\"name\":\"\",\"url\":\"\"}", any())
    }
}