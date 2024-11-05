package fr.mrsuricate.pokedex.ui.screen

import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fr.mrsuricate.pokedex.ui.component.setting.SettingsItem
import fr.mrsuricate.pokedex.ui.component.topBar.SettingAppBar
import fr.mrsuricate.pokedex.ui.viewModel.SettingViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingScreen(
    navController: NavController,
    onGoBack: () -> Unit
) {
    val settingViewModel: SettingViewModel = koinViewModel()

    // Get context to access package information
    val context = LocalContext.current
    val versionName = try {
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        packageInfo.versionName
    } catch (e: PackageManager.NameNotFoundException) {
        "N/A" // Default value if version not found
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SettingAppBar(title = "Paramètre") {
                onGoBack()
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Show list at top
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp) // To prevent the List from colliding with the Text
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
            // Shows version text centered at bottom
            Text(
                text = "v$versionName",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp) // To add a little margin at the bottom
            )
        }
    }
}
