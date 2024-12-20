package fr.mrsuricate.pokedex.ui.component.detail

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.mrsuricate.pokedex.ui.theme.PokedexTheme

@Composable
fun DetailPokemonProgressBarStats(
    statsType: String,
    stats: Int,
    color: Color
) {
    val maxStats = when {
        stats < 75 -> 150
        stats < 125 -> 250
        stats < 250 -> 500
        else -> 700
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(0.1f),
            text = statsType
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(
                        topStart = 30.dp,
                        topEnd = 30.dp,
                        bottomStart = 30.dp,
                        bottomEnd = 30.dp
                    )
                )
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(
                        topStart = 30.dp,
                        topEnd = 30.dp,
                        bottomStart = 30.dp,
                        bottomEnd = 30.dp
                    )
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(stats.toFloat() / maxStats.toFloat())
                    .background(
                        color = color,
                        shape = RoundedCornerShape(
                            topStart = 30.dp,
                            topEnd = 30.dp,
                            bottomStart = 30.dp,
                            bottomEnd = 30.dp
                        )
                    )
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 8.dp),
                    color = Color.White,
                    fontSize = 10.sp,
                    text = "${stats}/${maxStats}"
                )
            }
        }
    }

}

@Preview(name = "Dark Mode", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Preview(name = "Light Mode", showBackground = true, uiMode = UI_MODE_NIGHT_NO)
annotation class ThemePreviews

@ThemePreviews
@Composable
private fun PreviewTheme() {
    PokedexTheme {
        Surface {
            DetailPokemonProgressBarStats(
                statsType = "HP",
                stats = 100,
                color = Color.Red
            )
        }
    }
}

@Preview(
    name = "Landscape Mode",
    showBackground = true,
    device = Devices.AUTOMOTIVE_1024p,
    widthDp = 640
)
@Preview(name = "Portrait Mode", showBackground = true, device = Devices.PIXEL_4)
annotation class OrientationPreviews

@OrientationPreviews
@Composable
private fun PreviewOrientation() {
    PokedexTheme {
        Surface {
            DetailPokemonProgressBarStats(
                statsType = "HP",
                stats = 100,
                color = Color.Red
            )
        }
    }
}