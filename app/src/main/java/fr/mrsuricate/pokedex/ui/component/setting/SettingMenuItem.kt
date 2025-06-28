package fr.mrsuricate.pokedex.ui.component.setting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fr.mrsuricate.pokedex.ui.component.detail.OrientationPreviews
import fr.mrsuricate.pokedex.ui.component.detail.ThemePreviews
import fr.mrsuricate.pokedex.ui.theme.PokedexTheme

@Composable
fun SettingsMenuItem(
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 8.dp)
        ) {
            Text(
                text = text,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "See more",
                tint = Color.Gray,
            )
        }
    }

}

@ThemePreviews
@Composable
private fun PreviewTheme() {
    PokedexTheme {
        Surface {
            SettingsMenuItem("Language") {

            }
        }
    }
}

@OrientationPreviews
@Composable
private fun PreviewOrientation() {
    PokedexTheme {
        Surface {
            SettingsMenuItem("Language") {

            }
        }
    }
}