package fr.mrsuricate.pokedex.ui.component.topBar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import fr.mrsuricate.pokedex.ui.navigation.Setting
import fr.mrsuricate.pokedex.ui.theme.PokedexTheme
import fr.mrsuricate.pokedex.ui.theme.colorPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(modifier: Modifier = Modifier, navController: NavHostController) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Pokédex",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                IconButton(
                    onClick = {
                        navController.navigate(Setting.route)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }

        },
        colors = topAppBarColors(
            containerColor = colorPrimary
        ),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun PokedexAppBarPreview() {
    val navController = rememberNavController()
    PokedexTheme {
        HomeAppBar(navController = navController)
    }
}