package com.example.superhero

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.superhero.model.Hero
import com.example.superhero.model.HeroesRepository
import com.example.superhero.model.HeroesRepository.heroes


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroApp() {
    Scaffold(topBar = { SuperHeroAppBar() })
    { it ->
        LazyColumn(
            modifier = Modifier, contentPadding = it,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(heroes) {
                HeroCard(hero = it)
            }
        }
    }

}


@Composable
fun HeroCard(
    hero: Hero,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .height(104.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SuperHeroInformation(
                heroName = hero.nameRes,
                heroDescriptor = hero.descriptionRes,
                Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            SuperHeroImage(heroImage = hero.imageRes, Modifier.weight(0.25f))
        }
    }

}


@Composable
fun SuperHeroInformation(
    @StringRes heroName: Int,
    @StringRes heroDescriptor: Int,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Text(
            text = stringResource(id = heroName),
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            // modifier = Modifier.padding(end = 20.dp),
            text = stringResource(id = heroDescriptor),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun SuperHeroImage(
    @DrawableRes heroImage: Int,
    modifier: Modifier = Modifier,
) {
    Image(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .size(dimensionResource(id = R.dimen.padding_medium1)),
        contentScale = ContentScale.Crop,
        painter = painterResource(id = heroImage),
        contentDescription = null
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperHeroAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Super Hero",
                style = MaterialTheme.typography.displayLarge
            )
        })
}