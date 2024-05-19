package com.pocket.codelabsjetpackcompose.cupcakeapp.route

import androidx.annotation.StringRes
import com.pocket.codelabsjetpackcompose.R

enum class CupcakeScreen(@StringRes val title: Int) {
    Start(title = R.string.cupcake_app_name),
    CupCakeFlavor(title = R.string.choose_flavor),
    CupCakePickup(title = R.string.choose_pickup_date),
    CupCakeSummary(title = R.string.order_summary)
}