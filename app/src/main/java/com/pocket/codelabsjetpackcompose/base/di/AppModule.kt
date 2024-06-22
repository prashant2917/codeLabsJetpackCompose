package com.pocket.codelabsjetpackcompose.base.di

import android.content.Context
import com.pocket.codelabsjetpackcompose.inventory.data.InventoryDatabase

object AppModule {

    fun provideInventoryDataBase(context: Context): InventoryDatabase {
        return InventoryDatabase.getDatabase(context)
    }
}