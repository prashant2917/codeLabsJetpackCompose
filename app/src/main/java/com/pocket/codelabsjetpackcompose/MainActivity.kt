package com.pocket.codelabsjetpackcompose

import AppTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pocket.codelabsjetpackcompose.affirmations.AffirmationsApp
import com.pocket.codelabsjetpackcompose.cupcakeapp.data.DataSource
import com.pocket.codelabsjetpackcompose.cupcakeapp.presentation.CupcakeApp
import com.pocket.codelabsjetpackcompose.cupcakeapp.presentation.OrderSummaryScreen
import com.pocket.codelabsjetpackcompose.cupcakeapp.presentation.OrderViewModel
import com.pocket.codelabsjetpackcompose.cupcakeapp.presentation.SelectOptionScreen
import com.pocket.codelabsjetpackcompose.cupcakeapp.presentation.cancelOrderAndNavigateToStart
import com.pocket.codelabsjetpackcompose.cupcakeapp.presentation.shareOrder
import com.pocket.codelabsjetpackcompose.cupcakeapp.route.CupcakeScreen
import com.pocket.codelabsjetpackcompose.diceroller.DiceRollerApp
import com.pocket.codelabsjetpackcompose.greeting.GreetingImage
import com.pocket.codelabsjetpackcompose.home.HomeScreenRoute
import com.pocket.codelabsjetpackcompose.home.presentation.HomeScreenApp
import com.pocket.codelabsjetpackcompose.marsphoto.presentation.MarsPhotosApp
import com.pocket.codelabsjetpackcompose.tipcalculator.TipCalculatorApp
import com.pocket.codelabsjetpackcompose.unscramblegame.presentation.UnscrambleWordsGameScreen
import com.pocket.codelabsjetpackcompose.woofapp.presentation.WoofApp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                 //HomeScreenApp()
                   val navController: NavHostController = rememberNavController()
                    val backStackEntry by navController.currentBackStackEntryAsState()
                    // Get the name of the current screen
                    val currentScreen = HomeScreenRoute.valueOf(
                        backStackEntry?.destination?.route ?: HomeScreenRoute.HomeScreen.name
                    )

                    Scaffold(
                        topBar = {
                            AppTopBar(
                                currentScreen = currentScreen,
                                canNavigateBack = navController.previousBackStackEntry != null,
                                navigateUp = { navController.navigateUp() }
                            )
                        }
                    ) { innerPadding ->
                        AppNavHost(navController = navController, innerPadding = innerPadding)
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AppTopBar(
        currentScreen: HomeScreenRoute,
        canNavigateBack: Boolean,
        navigateUp: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        TopAppBar(
            title = { Text(stringResource(currentScreen.title)) },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            modifier = modifier,
            navigationIcon = {
                if (canNavigateBack) {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }
                }
            }
        )
    }

    @Composable
    fun AppNavHost(navController : NavHostController, innerPadding: PaddingValues) {
       val orderViewModel: OrderViewModel = viewModel()
        val orderUiState by orderViewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = HomeScreenRoute.HomeScreen.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(route = HomeScreenRoute.HomeScreen.name) {
                HomeScreenApp( modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(R.dimen.padding_medium)),  contentPadding = PaddingValues(16.dp), navController =  navController)
            }
            composable(route = HomeScreenRoute.GreetingScreen.name) {
                GreetingImage(message = "Happy Birthday", from = "John")

            }
            composable(route = HomeScreenRoute.DiceRollScreen.name) {
                DiceRollerApp()
            }
            composable(route = HomeScreenRoute.WoofListScreen.name) {

                WoofApp()
            }

            composable(route = HomeScreenRoute.AffirmationApp.name) {
                AffirmationsApp()
            }

            composable(route = HomeScreenRoute.UnscrambleWordScreen.name) {
              UnscrambleWordsGameScreen()
            }

            composable(route = HomeScreenRoute.TipCalculatorScreen.name) {
                TipCalculatorApp()
            }

            composable(route = HomeScreenRoute.MarsPhoto.name) {
                MarsPhotosApp()
            }

            composable(route = HomeScreenRoute.CupCakeOrderScreen.name) {
                CupcakeApp(viewModel = orderViewModel, navController = navController)
            }

            composable(route = CupcakeScreen.CupCakeFlavor.name) {
                val context = LocalContext.current
                SelectOptionScreen(
                    subtotal = orderUiState.price,
                    onNextButtonClicked = { navController.navigate(CupcakeScreen.CupCakePickup.name) },
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToStart(orderViewModel, navController)
                    },
                    options = DataSource.flavors.map { id -> context.resources.getString(id) },
                    onSelectionChanged = { orderViewModel.setFlavor(it) },
                    modifier = Modifier.fillMaxHeight()
                )
            }
            composable(route = CupcakeScreen.CupCakePickup.name) {
                SelectOptionScreen(
                    subtotal = orderUiState.price,
                    onNextButtonClicked = { navController.navigate(CupcakeScreen.CupCakeSummary.name) },
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToStart(orderViewModel, navController)
                    },
                    options = orderUiState.pickupOptions,
                    onSelectionChanged = { orderViewModel.setDate(it) },
                    modifier = Modifier.fillMaxHeight()
                )
            }
            composable(route = CupcakeScreen.CupCakeSummary.name) {
                val context = LocalContext.current
                OrderSummaryScreen(
                    orderUiState = orderUiState,
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToStart(orderViewModel, navController)
                    },
                    onSendButtonClicked = { subject: String, summary: String ->
                        shareOrder(context, subject = subject, summary = summary)
                    },
                    modifier = Modifier.fillMaxHeight()
                )
            }

        }
    }
}

