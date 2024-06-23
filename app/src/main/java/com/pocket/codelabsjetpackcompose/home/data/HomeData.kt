package com.pocket.codelabsjetpackcompose.home.data

import androidx.annotation.StringRes
import com.pocket.codelabsjetpackcompose.R
import com.pocket.codelabsjetpackcompose.home.HomeScreenRoute

data class HomeScreenData(@StringRes val title: Int, val route: HomeScreenRoute)

fun getHomeDataList(): List<HomeScreenData> {
    val list = mutableListOf<HomeScreenData>()
    list.add(HomeScreenData(R.string.greeting_app_name, HomeScreenRoute.GreetingScreen))
    list.add(HomeScreenData(R.string.dice_roll_app, HomeScreenRoute.DiceRollScreen))
    list.add(HomeScreenData(R.string.tip_calculator_app, HomeScreenRoute.TipCalculatorScreen))
    list.add(HomeScreenData(R.string.affirmation_app, HomeScreenRoute.AffirmationApp))
    list.add(HomeScreenData(R.string.woof_app_name, HomeScreenRoute.WoofListScreen))
    list.add(HomeScreenData(R.string.unscramble_app_name, HomeScreenRoute.UnscrambleWordScreen))
    list.add(HomeScreenData(R.string.cupcake_app_name, HomeScreenRoute.CupCakeOrderScreen))
    list.add(HomeScreenData(R.string.mars_app_name, HomeScreenRoute.MarsPhoto))
    list.add(HomeScreenData(R.string.inventory_app_name, HomeScreenRoute.InventoryApp))
    return list
}