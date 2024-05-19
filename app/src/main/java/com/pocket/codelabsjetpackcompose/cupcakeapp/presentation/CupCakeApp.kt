package com.pocket.codelabsjetpackcompose.cupcakeapp.presentation

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavHostController
import com.pocket.codelabsjetpackcompose.R
import com.pocket.codelabsjetpackcompose.cupcakeapp.data.DataSource
import com.pocket.codelabsjetpackcompose.home.HomeScreenRoute

@Composable
fun CupcakeApp(
    viewModel: OrderViewModel,
    navController: NavHostController
) {

    StartOrderScreen(
        quantityOptions = DataSource.quantityOptions,
        onNextButtonClicked = {
            viewModel.setQuantity(it)
            navController.navigate(HomeScreenRoute.CupCakeFlavor.name)
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium))
    )
}

/**
 * Resets the [OrderUiState] and pops up to [CupcakeScreen.Start]
 */
fun cancelOrderAndNavigateToStart(
    viewModel: OrderViewModel,
    navController: NavHostController
) {
    viewModel.resetOrder()
    navController.popBackStack(HomeScreenRoute.CupCakeOrderScreen.name, inclusive = false)
}

/**
 * Creates an intent to share order details
 */
fun shareOrder(context: Context, subject: String, summary: String) {
    // Create an ACTION_SEND implicit intent with order details in the intent extras
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, summary)
    }
    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.new_cupcake_order)
        )
    )
}