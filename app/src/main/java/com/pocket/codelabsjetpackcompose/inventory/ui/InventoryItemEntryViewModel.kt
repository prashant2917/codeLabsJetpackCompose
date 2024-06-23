
package com.pocket.codelabsjetpackcompose.inventory.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.pocket.codelabsjetpackcompose.inventory.data.InventoryItem
import com.pocket.codelabsjetpackcompose.inventory.data.InventoryItemsRepository

import java.text.NumberFormat

/**
 * ViewModel to validate and insert items in the Room database.
 */
class InventoryItemEntryViewModel(private val itemsRepository: InventoryItemsRepository) : ViewModel() {

    /**
     * Holds current item ui state
     */
    var inventoryItemUiState by mutableStateOf(InventoryItemUiState())
        private set

    /**
     * Updates the [inventoryItemUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(inventoryItemDetails: InventoryItemDetails) {
        inventoryItemUiState =
            InventoryItemUiState(inventoryItemDetails = inventoryItemDetails, isEntryValid = validateInput(inventoryItemDetails))
    }

    suspend fun saveItem() {
        if (validateInput()) {
            itemsRepository.insertItem(inventoryItemUiState.inventoryItemDetails.toItem())
        }
    }

    private fun validateInput(uiState: InventoryItemDetails = inventoryItemUiState.inventoryItemDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }
}

/**
 * Represents Ui State for an Item.
 */
data class InventoryItemUiState(
    val inventoryItemDetails: InventoryItemDetails = InventoryItemDetails(),
    val isEntryValid: Boolean = false
)

data class InventoryItemDetails(
    val id: Int = 0,
    val name: String = "",
    val price: String = "",
    val quantity: String = "",
)

/**
 * Extension function to convert [InventoryItemDetails] to [Item]. If the value of [InventoryItemDetails.price] is
 * not a valid [Double], then the price will be set to 0.0. Similarly if the value of
 * [InventoryItemDetails.quantity] is not a valid [Int], then the quantity will be set to 0
 */
fun InventoryItemDetails.toItem(): InventoryItem = InventoryItem(
    id = id,
    name = name,
    price = price.toDoubleOrNull() ?: 0.0,
    quantity = quantity.toIntOrNull() ?: 0
)

fun InventoryItem.formatedPrice(): String {
    return NumberFormat.getCurrencyInstance().format(price)
}

/**
 * Extension function to convert [Item] to [InventoryItemUiState]
 */
fun InventoryItem.toInventoryItemUiState(isEntryValid: Boolean = false): InventoryItemUiState = InventoryItemUiState(
    inventoryItemDetails = this.toInventoryItemDetails(),
    isEntryValid = isEntryValid
)

/**
 * Extension function to convert [Item] to [InventoryItemDetails]
 */
fun InventoryItem.toInventoryItemDetails(): InventoryItemDetails = InventoryItemDetails(
    id = id,
    name = name,
    price = price.toString(),
    quantity = quantity.toString()
)
