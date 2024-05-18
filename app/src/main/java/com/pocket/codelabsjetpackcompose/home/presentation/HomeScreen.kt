package com.pocket.codelabsjetpackcompose.home.presentation

import AppTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pocket.codelabsjetpackcompose.R
import com.pocket.codelabsjetpackcompose.home.HomeScreenRoute
import com.pocket.codelabsjetpackcompose.home.data.HomeScreenData
import com.pocket.codelabsjetpackcompose.home.data.getHomeDataList


@Composable
fun HomeScreenApp(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    navController: NavHostController
) {
    val appList = getHomeDataList()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.padding(horizontal = 4.dp),
        contentPadding = contentPadding,
    ) {
        items(items = appList, key = { appData -> appData.title }) {
            appdata ->
            HomeScreenCard(
                appdata, modifier = modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .aspectRatio(1.5f),
                navController
            )
        }
    }
}

@Composable
fun HomeScreenCard(homeScreenData: HomeScreenData, modifier: Modifier = Modifier, navController: NavHostController) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        onClick = {
            navController.navigate(homeScreenData.route.name)
        }
    ) {

        Box( contentAlignment = Alignment.Center, modifier = Modifier
            .fillMaxSize()
            .padding(8.dp) ) {
            Text(
                text = stringResource(id = homeScreenData.title),
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center
            )
        }
    }
}



@Preview
@Composable
fun previewHomeScreenCard() {
    AppTheme {
        HomeScreenCard(homeScreenData = HomeScreenData(R.string.unscramble_app_name, HomeScreenRoute.UnscrambleWordScreen), navController = rememberNavController())
    }
}

