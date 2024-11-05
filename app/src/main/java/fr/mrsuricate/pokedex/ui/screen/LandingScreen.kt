package fr.mrsuricate.pokedex.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import fr.mrsuricate.pokedex.R
import fr.mrsuricate.pokedex.ui.theme.PokedexTheme
import fr.mrsuricate.pokedex.ui.viewModel.HomeViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

private const val SplashWaitTime: Long = 2000

@Composable
fun LandingScreen(modifier: Modifier = Modifier) {

    // Get the HomeViewModel from Koin
    val homeViewModel: HomeViewModel = koinViewModel()

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LaunchedEffect(Unit) {
            delay(SplashWaitTime)
            homeViewModel.setShowHomeScreen(true)
        }
        Image(
            painterResource(
                id = R.drawable.ic_launcher_foreground
            ),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LandingScreenPreview() {
    PokedexTheme {
        LandingScreen()
    }
}