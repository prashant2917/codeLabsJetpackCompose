package com.pocket.codelabsjetpackcompose.cupcakeapp.route

import androidx.annotation.StringRes
import com.pocket.codelabsjetpackcompose.R

enum class CupcakeScreen(@StringRes val title: Int) {
    Start(title = R.string.cupcake_app_name),
    Flavor(title = R.string.choose_flavor),
    Pickup(title = R.string.choose_pickup_date),
    Summary(title = R.string.order_summary)
}