package com.pocket.codelabsjetpackcompose.inventory.data

import kotlinx.coroutines.flow.Flow

class OfflineInventoryItemsRepository(private val itemDao: InventoryItemDao) :
    InventoryItemsRepository {
    override fun getAllItemsStream(): Flow<List<InventoryItem>> = itemDao.getAllItems()

    override fun getItemStream(id: Int): Flow<InventoryItem?> = itemDao.getItem(id)

    override suspend fun insertItem(item: InventoryItem) = itemDao.insert(item)

    override suspend fun deleteItem(item: InventoryItem) = itemDao.delete(item)

    override suspend fun updateItem(item: InventoryItem) = itemDao.update(item)
}
