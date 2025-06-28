package fr.mrsuricate.pokedex.ui.navigation

sealed class Screen(val route: String)
data object Home : Screen("/home")
data object Detail : Screen("/detail")
data object Setting : Screen("/setting")
data object SettingLanguage : Screen("/setting/language")
data object SettingClearCache : Screen("/setting/clearCache")
