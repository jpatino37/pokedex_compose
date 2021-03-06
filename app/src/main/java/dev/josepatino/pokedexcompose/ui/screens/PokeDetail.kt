package dev.josepatino.pokedexcompose.ui.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.palette.graphics.Palette
import dev.josepatino.pokedexcompose.model.FavoritePokemon
import dev.josepatino.pokedexcompose.model.PokemonInfo
import dev.josepatino.pokedexcompose.ui.components.ChipRow
import dev.josepatino.pokedexcompose.ui.components.Measurement
import dev.josepatino.pokedexcompose.ui.components.PokeImageCard
import dev.josepatino.pokedexcompose.ui.components.StatBar

@ExperimentalAnimationApi
@Composable
fun PokeDetail(
    pokeInfo: PokemonInfo?,
    onPaletteColorChange: (Palette) -> Unit,
    palette: Palette?,
    isFavorite: Boolean,
    onFavoriteToggle: (FavoritePokemon) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        color = MaterialTheme.colors.background
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            PokeImageCard(
                pokemon = pokeInfo,
                onPaletteColorChange = onPaletteColorChange,
                palette = palette,
                isFavorite = isFavorite,
                onFavoriteToggle = onFavoriteToggle
            )
            Text(
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 0.dp),
                text = pokeInfo?.name ?: "",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = White
            )
            Text(
                "Base EXP: ${pokeInfo?.baseExperience}",
                fontSize = 18.sp,
                color = White,
            )
            Column(
                modifier = Modifier
                    .padding(start = 30.dp, end = 30.dp, top = 20.dp, bottom = 0.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ChipRow(pokeInfo?.types ?: emptyList())
                Row(
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Measurement(measurement = "${pokeInfo?.weight} KG", category = "Weight")
                    Measurement(measurement = "${pokeInfo?.height} M", category = "Height")
                }
                Text(
                    modifier = Modifier.padding(top = 15.dp),
                    text = "Base Stats",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = White
                )
                pokeInfo?.let {
                    it.stats.forEach {
                        StatBar(it)
                    }
                }
            }
        }
    }
}