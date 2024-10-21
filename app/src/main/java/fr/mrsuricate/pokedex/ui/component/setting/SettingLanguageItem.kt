package fr.mrsuricate.pokedex.ui.component.setting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.mrsuricate.pokedex.ui.theme.PokedexTheme

@Composable
fun SettingLanguageItem(
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
        }
    }
}

@Preview
@Composable
private fun SettingLanguageItemPreview() {
    PokedexTheme(
        darkTheme = true
    ) {
        SettingLanguageItem(
            text = "English",
            onClick = {}
        )
    }
}