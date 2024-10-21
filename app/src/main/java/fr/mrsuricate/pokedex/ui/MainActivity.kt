package fr.mrsuricate.pokedex.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.mrsuricate.pokedex.ui.navigation.Detail
import fr.mrsuricate.pokedex.ui.navigation.Home
import fr.mrsuricate.pokedex.ui.navigation.Landing
import fr.mrsuricate.pokedex.ui.navigation.Setting
import fr.mrsuricate.pokedex.ui.navigation.SettingLanguage
import fr.mrsuricate.pokedex.ui.screen.DetailScreen
import fr.mrsuricate.pokedex.ui.screen.HomeScreen
import fr.mrsuricate.pokedex.ui.screen.LandingScreen
import fr.mrsuricate.pokedex.ui.screen.SettingLanguageScreen
import fr.mrsuricate.pokedex.ui.screen.SettingScreen
import fr.mrsuricate.pokedex.ui.theme.PokedexTheme
import fr.mrsuricate.pokedex.ui.viewModel.DetailViewModel
import fr.mrsuricate.pokedex.ui.viewModel.HomeViewModel
import fr.mrsuricate.pokedex.ui.viewModel.SettingViewModel

// Main application activity
class MainActivity : ComponentActivity() {

    // Method that runs when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enables edge-to-edge layout, allowing the UI to extend to the edges of the screen
        enableEdgeToEdge()

        // Sets the content view using Jetpack Compose
        setContent {
            // Applies the application's theme
            PokedexTheme {

                // Creates and remembers a NavController for handling screen navigation
                val navController = rememberNavController()

                // Creates an instance of HomeViewModel
                val homeViewModel: HomeViewModel = viewModel()

                // Creates an instance of SettingsViewModel
                val settingViewModel: SettingViewModel = viewModel()

                // Creates an instance of DetailViewModel
                val detailViewModel: DetailViewModel = viewModel()

                // Defines the navigation structure with NavHost
                NavHost(
                    navController = navController,
                    startDestination = Landing.route // Sets the starting screen to Landing
                ) {

                    // Defines navigation to the landing screen
                    composable(route = Landing.route) {
                        // Displays the LandingScreen component and navigates to the Home screen after a timeout
                        LandingScreen(onTimeout = {
                            navController.navigate(Home.route) // Navigate to the Home screen
                        })
                    }

                    // Defines navigation to the home page
                    composable(route = Home.route) {
                        HomeScreen(
                            homeViewModel = homeViewModel,
                            detailViewModel = detailViewModel,
                            settingViewModel = settingViewModel,
                            navController = navController
                        )
                    }

                    // Defines navigation to the detail page
                    composable(
                        route = Detail.route
                    ) {
                        DetailScreen(
                            settingViewModel = settingViewModel,
                            detailViewModel = detailViewModel
                        ) {
                            navController.popBackStack() // Navigate back in the stack
                        }
                    }


                    // Defines navigation to the setting page
                    composable(
                        route = Setting.route
                    ) {
                        SettingScreen(
                            settingViewModel = settingViewModel,
                            navController = navController
                        ) {
                            navController.popBackStack() // Navigate back in the stack
                        }
                    }

                    // Defines navigation to the setting language page
                    composable(
                        route = SettingLanguage.route
                    ) {
                        SettingLanguageScreen(
                            settingViewModel = settingViewModel,
                        ) {
                            navController.popBackStack() // Navigate back in the stack
                        }
                    }
                }
            }
        }
    }
}