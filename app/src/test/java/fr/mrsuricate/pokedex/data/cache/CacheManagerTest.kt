package fr.mrsuricate.pokedex.data.cache

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
        cacheManager.cacheValue("", fileName)

        // Then
        verify(fileManager).saveData(any(), eq("pokemon.txt"))
    }

    @Test
    fun `should create file with pokemon name`() {
        // Given
        val fileManager: FileManager = mock { }
        val cacheManager = CacheManager(fileManager)
        val fileName: String = "https://pokeapi.co/api/v2/pokemon/pikachu"

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
        val data: Any = "data"
        val fileName: String = "https://pokeapi.co/api/v2/language"

        // When
        cacheManager.cacheValue("", fileName)

        // Then
        verify(fileManager).saveData(any(), eq("language.txt"))
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
            on { loadData(any()) }.thenReturn("""{"name":"Pikachu","url":"https://pokeapi.co/api/v2/pokemon/pikachu"}""")
        }
        val cacheManager = CacheManager(fileManager)

        // When
        val result = cacheManager.retrieveValue(
            "https://pokeapi.co/api/v2/pokemon/pikachu",
            NamedApiResource::class.java
        )

        // Then
        assert(result.name == "Pikachu")
    }
}