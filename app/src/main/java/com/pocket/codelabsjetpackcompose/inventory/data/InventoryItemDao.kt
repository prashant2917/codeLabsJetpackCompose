package com.pocket.codelabsjetpackcompose.inventory.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.pocket.codelabsjetpackcompose.affirmations.utils.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface InventoryItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: InventoryItem)

    @Update
    suspend fun update(item: InventoryItem)

    @Delete
    suspend fun delete(item: InventoryItem)

    @Query("SELECT * from ${Constants.DatabaseConstants.TABLE_INVENTORY_ITEM} WHERE id = :id")
    fun getItem(id: Int): Flow<InventoryItem>

    @Query("SELECT * from  ${Constants.DatabaseConstants.TABLE_INVENTORY_ITEM} ORDER BY name ASC")
    fun getAllItems(): Flow<List<InventoryItem>>
}