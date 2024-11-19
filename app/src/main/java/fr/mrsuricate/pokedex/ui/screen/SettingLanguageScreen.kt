package fr.mrsuricate.pokedex.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import fr.mrsuricate.pokedex.ui.component.setting.SettingLanguageItem
import fr.mrsuricate.pokedex.ui.component.topBar.SettingAppBar
import fr.mrsuricate.pokedex.ui.viewModel.SettingViewModel
import org.koin.androidx.compose.koinViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SettingLanguageScreen(onGoBack: () -> Unit) {
    val settingViewModel: SettingViewModel = koinViewModel()
    val languageList = settingViewModel.languageFlow.value
    val selectedLanguage = settingViewModel.getSelectedLangue()

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
            items(languageList.size) { index ->
                val language = languageList[index]

                if (language.official) {
                    val displayName = language.names.find { it.language == selectedLanguage }
                        ?: language.names.find { it.language == "fr" }

                    displayName?.let {
                        SettingLanguageItem(
                            text = it.name,
                            selectedLanguage = language.language == selectedLanguage,
                            onClick = {
                                settingViewModel.setSelectedLangue(language.language)
                                onGoBack()
                            }
                        )
                    }
                }
            }
        }
    }
}