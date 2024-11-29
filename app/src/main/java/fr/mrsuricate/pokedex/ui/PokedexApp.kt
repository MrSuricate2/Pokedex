package fr.mrsuricate.pokedex.ui

import android.app.Application
import fr.mrsuricate.pokedex.ui.koin.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PokedexApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PokedexApp)
            modules(appModule)
        }
    }

}