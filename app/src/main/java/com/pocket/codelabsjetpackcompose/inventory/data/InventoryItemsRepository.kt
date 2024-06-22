package com.pocket.codelabsjetpackcompose.inventory.data

import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [Item] from a given data source.
 */
interface InventoryItemsRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllItemsStream(): Flow<List<InventoryItem>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getItemStream(id: Int): Flow<InventoryItem?>

    /**
     * Insert item in the data source
     */
    suspend fun insertItem(item: InventoryItem)

    /**
     * Delete item from the data source
     */
    suspend fun deleteItem(item: InventoryItem)

    /**
     * Update item in the data source
     */
    suspend fun updateItem(item: InventoryItem)
}