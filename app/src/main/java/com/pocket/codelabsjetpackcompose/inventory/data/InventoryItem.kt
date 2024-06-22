package com.pocket.codelabsjetpackcompose.inventory.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pocket.codelabsjetpackcompose.affirmations.utils.Constants

/**
 * Entity data class represents a single row in the database.
 */
@Entity(tableName = Constants.DatabaseConstants.TABLE_INVENTORY_ITEM)
data class InventoryItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val price: Double,
    val quantity: Int
)