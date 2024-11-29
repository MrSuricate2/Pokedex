package fr.mrsuricate.pokedex.data.cache

import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class CacheManagerTest {

    @Test
    fun `should save data`() {
        val fileManager: FileManager = mock { }
        val cacheManager = CacheManager(fileManager)
        val data: String = "data"
        val fileName: String = "file"

        cacheManager.setCache(data, fileName)

        verify(fileManager).saveData(eq(data), eq(fileName))
    }


    @Test
    fun `should load data`() {
        val fileManager: FileManager = mock {
            on { loadData(any()) }.thenReturn("{}")
        }
        val cacheManager = CacheManager(fileManager)
        val fileName: String = "file"

        cacheManager.getCache(fileName)

        verify(fileManager).loadData(eq(fileName))
    }
}