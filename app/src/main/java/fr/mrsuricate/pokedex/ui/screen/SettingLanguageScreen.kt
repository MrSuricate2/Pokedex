package fr.mrsuricate.pokedex.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.mrsuricate.pokedex.ui.component.setting.SettingLanguageItem
import fr.mrsuricate.pokedex.ui.component.setting.SettingsItem
import fr.mrsuricate.pokedex.ui.component.topBar.SettingAppBar
import fr.mrsuricate.pokedex.ui.theme.PokedexTheme
import fr.mrsuricate.pokedex.ui.viewModel.SettingViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SettingLanguageScreen(settingViewModel: SettingViewModel, onGoBack: () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SettingAppBar(title = "Langue") {
                onGoBack()
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            items(settingViewModel.language.size) { index ->
                settingViewModel.language.values.elementAt(index).names.find {
                    it.language.name == settingViewModel.selectedLanguage.value?.name
                }?.let {
                    SettingLanguageItem(
                        text = it.name,
                        onClick = {
                            settingViewModel.changeSelectedLangue(
                                settingViewModel.language.values.elementAt(
                                    index
                                )
                            )
                            onGoBack()
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun SettingScreenPreview() {
    PokedexTheme(
        darkTheme = true
    ) {
        SettingLanguageScreen(
            settingViewModel = SettingViewModel()
        ) {

        }
    }
}

@Preview
@Composable
private fun SettingsItemPreview() {
    PokedexTheme(
        darkTheme = true
    ) {
        SettingsItem("Langue") {

        }
    }
}