package fr.mrsuricate.pokedex.data.cache

import fr.mrsuricate.pokedex.data.api.model.NamedApiResource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
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
        val fileName = "https://pokeapi.co/api/v2/pokemon"

        // When
        cacheManager.cacheValue("", fileName)

        // Then
        verify(fileManager).saveData(any(), eq("pokemon.txt"))
    }

    @Test
    fun `should create file with pokemon name`() {
        // Given
        val fileManager: FileManager = mock { }
        val cacheManager = CacheManager(fileManager)
        val fileName = "https://pokeapi.co/api/v2/pokemon/pikachu"

        // When
        cacheManager.cacheValue("", fileName)

        // Then
        verify(fileManager).saveData(any(), eq("pokemon_pikachu.txt"))
    }

    @Test
    fun `should create file with specific URL`() {
        // Given
        val fileManager: FileManager = mock { }
        val cacheManager = CacheManager(fileManager)
        val fileName = "https://pokeapi.co/api/v2/language"

        // When
        cacheManager.cacheValue("", fileName)

        // Then
        verify(fileManager).saveData(any(), eq("language.txt"))
    }

    @Test
    fun `should create file with specific URL and one parameter`() {
        // Given
        val fileManager: FileManager = mock { }
        val cacheManager = CacheManager(fileManager)
        val fileName = "https://pokeapi.co/api/v2/pokemon?offset=100"

        // When
        cacheManager.cacheValue("", fileName)

        // Then
        verify(fileManager).saveData(any(), eq("pokemon_offset_100.txt"))
    }

    @Test
    fun `should create file with specific URL and multiple parameter`() {
        // Given
        val fileManager: FileManager = mock { }
        val cacheManager = CacheManager(fileManager)
        val fileName = "https://pokeapi.co/api/v2/pokemon?offset=100&limit=1000"

        // When
        cacheManager.cacheValue("", fileName)

        // Then
        verify(fileManager).saveData(any(), eq("pokemon_offset_100_limit_1000.txt"))
    }

    @Test
    fun `should create file with serialize objet`() {
        // Given
        val fileManager: FileManager = mock { }
        val cacheManager = CacheManager(fileManager)

        // When
        cacheManager.cacheValue(
            data = NamedApiResource(),
            url = "https://pokeapi.co/api/v2/language"
        )

        val data = """{"name":"","url":""}"""

        // Then
        verify(fileManager).saveData(eq(data), any())
    }

    @Test
    fun `should retrieve object from cache`() {
        // Given
        val fileManager: FileManager = mock {
            on { loadData("pokemon_pikachu.txt") }.thenReturn("""{"name":"Pikachu","url":"https://pokeapi.co/api/v2/pokemon/pikachu"}""")
        }
        val cacheManager = CacheManager(fileManager)

        // When
        val result = cacheManager.retrieveValue(
            "https://pokeapi.co/api/v2/pokemon/pikachu",
            NamedApiResource::class.java
        )

        // Then
        assertEquals(
            NamedApiResource("Pikachu", "https://pokeapi.co/api/v2/pokemon/pikachu"),
            result
        )
    }

    @Test
    fun `should return null for non-existing cache file`() {
        // Given
        val fileManager: FileManager = mock {
            on { loadData("pokemon_no_existing.txt") }.thenReturn(null)
        }
        val cacheManager = CacheManager(fileManager)

        // When
        val result: NamedApiResource = cacheManager.retrieveValue(
            "https://pokeapi.co/api/v2/no_existing",
            NamedApiResource::class.java
        )

        // Then
        assertNull(result)
    }
}