package fr.mrsuricate.pokedex.ui.component.home

import android.content.res.Configuration
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fr.mrsuricate.pokedex.ui.viewModel.HomeViewModel
import org.koin.androidx.compose.koinViewModel
import kotlin.math.roundToInt

@Composable
fun PokemonListCard(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onRefresh: () -> Unit = {},
    isRefreshing: Boolean = false
) {
    // ViewModel singleton injection
    val homeViewModel: HomeViewModel = koinViewModel()

    val pokemonList by homeViewModel.pokemonFlow.collectAsState()
    val isLoading by homeViewModel.isLoading.collectAsState()

    val listState = rememberLazyGridState()
    val density = LocalDensity.current

    // Pull to refresh state
    var pullOffset by remember { mutableFloatStateOf(0f) }
    val pullThreshold = with(density) { 80.dp.toPx() }

    // Check if we're at the top
    val isAtTop by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0
        }
    }

    // Retrieves current configuration
    val configuration = LocalConfiguration.current
    // Sets the number of columns based on orientation
    val columns = if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        3
    } else {
        2
    }

    if (pokemonList.isEmpty() && isLoading) {
        // Show loading indicator when list is empty and loading
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = modifier
        ) {
            // Pull to refresh indicator
            if (pullOffset > 0 || isRefreshing) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    if (isRefreshing || pullOffset >= pullThreshold) {
                        CircularProgressIndicator()
                    } else {
                        Text("Pull to refresh")
                    }
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(columns),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .offset { IntOffset(0, pullOffset.roundToInt()) }
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragEnd = {
                                if (pullOffset >= pullThreshold && isAtTop && !isRefreshing) {
                                    onRefresh()
                                }
                                pullOffset = 0f
                            }
                        ) { _, dragAmount ->
                            if (isAtTop && !isRefreshing) {
                                val newOffset = pullOffset + dragAmount.y
                                pullOffset =
                                    newOffset.coerceAtLeast(0f).coerceAtMost(pullThreshold * 1.5f)
                            }
                        }
                    }
                    .padding(top = 8.dp)
            ) {
                items(count = pokemonList.size) { index ->
                    pokemonList[index].let { pokemon ->
                        PokemonCard(
                            pokemon = pokemon,
                            navController = navController
                        )

                        // Load more when approaching the end of the list (only if not already loading)
                        if (index == (pokemonList.size.minus(10)) && !isLoading) {
                            LaunchedEffect(Unit) {
                                homeViewModel.loadMorePokemon()
                            }
                        }
                    }
                }

                // Show loading indicator at the bottom when loading more
                if (isLoading && pokemonList.isNotEmpty() && !isRefreshing) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}