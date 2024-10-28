package fr.mrsuricate.pokedex.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import fr.mrsuricate.pokedex.ui.component.setting.SettingsItem
import fr.mrsuricate.pokedex.ui.component.topBar.SettingAppBar
import fr.mrsuricate.pokedex.ui.theme.PokedexTheme
import fr.mrsuricate.pokedex.ui.viewModel.SettingViewModel
import org.koin.androidx.compose.koinViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SettingScreen(
    navController: NavController,
    onGoBack: () -> Unit
) {
    val settingViewModel: SettingViewModel = koinViewModel()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SettingAppBar(title = "Paramètre") {
                onGoBack()
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            items(settingViewModel.settings.size) { index ->
                SettingsItem(
                    text = settingViewModel.settings.keys.elementAt(index),
                    onClick = {
                        navController.navigate(settingViewModel.settings.values.elementAt(index))
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun SettingScreenPreview() {
    val navController = rememberNavController()
    PokedexTheme(
        darkTheme = true
    ) {
        SettingScreen(
            navController = navController
        ) {

        }
    }
}

