package fr.mrsuricate.pokedex.ui.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.size.Size
import coil3.svg.SvgDecoder
import fr.mrsuricate.pokedex.data.api.model.PokemonJsonModel

@Composable
fun DisplayPokemon(
    pokemon: PokemonJsonModel,
    modifier: Modifier = Modifier
) {
    if (pokemon.sprites.other.dreamWorld.frontDefault != null) {
        SvgImage(
            url = pokemon.sprites.other.dreamWorld.frontDefault!!,
            contentDescription = "${pokemon.name} dreamWorld picture",
            modifier = modifier
        )
    } else if (pokemon.sprites.other.officialArtwork.frontDefault != null) {
        AsyncImage(
            model = pokemon.sprites.other.officialArtwork.frontDefault,
            contentDescription = "${pokemon.name} officialArtwork picture",
            modifier = modifier,
            contentScale = ContentScale.Crop
        )
    } else {
        AsyncImage(
            model = pokemon.sprites.frontDefault,
            contentDescription = "${pokemon.name} default front picture",
            modifier = modifier,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun SvgImage(url: String, contentDescription: String?, modifier: Modifier = Modifier) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .decoderFactory(SvgDecoder.Factory())
            .size(Size.ORIGINAL)
            .build()
    )
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier,
    )
}