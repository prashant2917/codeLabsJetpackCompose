package com.pocket.codelabsjetpackcompose.base.di


import com.pocket.codelabsjetpackcompose.inventory.data.InventoryItemsRepository
import com.pocket.codelabsjetpackcompose.marsphoto.data.MarsPhotosRepository

interface AppContainer {
    val marsPhotosRepository: MarsPhotosRepository
    val itemsRepository: InventoryItemsRepository
}