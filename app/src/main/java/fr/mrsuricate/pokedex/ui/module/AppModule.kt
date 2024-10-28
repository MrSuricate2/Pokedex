package fr.mrsuricate.pokedex.ui.module

import fr.mrsuricate.pokedex.ui.viewModel.DetailViewModel
import fr.mrsuricate.pokedex.ui.viewModel.HomeViewModel
import fr.mrsuricate.pokedex.ui.viewModel.SettingViewModel
import org.koin.dsl.module

val appModule = module {
    single { HomeViewModel() }
    single { SettingViewModel() }
    single { DetailViewModel() }
}