package com.pocket.codelabsjetpackcompose.home

import androidx.annotation.StringRes
import com.pocket.codelabsjetpackcompose.R

enum class HomeScreenRoute(@StringRes val title: Int) {
    HomeScreen(title = R.string.app_name),
    GreetingScreen(title = R.string.greeting_app_name),
    DiceRollScreen(title = R.string.dice_roll_app),
    UnscrambleWordScreen(title = R.string.unscramble_app_name),
    TipCalculatorScreen(title = R.string.tip_calculator_app),
    WoofListScreen(title = R.string.woof_app_name),
    CupCakeOrderScreen(title = R.string.cupcake_app_name),
    CupCakeFlavor(title = R.string.choose_flavor),
    CupCakePickup(title = R.string.choose_pickup_date),
    CupCakeSummary(title = R.string.order_summary),
    MarsPhoto(title = R.string.mars_photo),
    AffirmationApp(title = R.string.affirmation_app),
}