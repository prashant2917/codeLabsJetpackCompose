package com.pocket.codelabsjetpackcompose.base.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pocket.codelabsjetpackcompose.base.CodeLabsApplication
import com.pocket.codelabsjetpackcompose.inventory.ui.InventoryItemDetailsViewModel
import com.pocket.codelabsjetpackcompose.inventory.ui.InventoryItemEditViewModel
import com.pocket.codelabsjetpackcompose.inventory.ui.InventoryItemEntryViewModel
import com.pocket.codelabsjetpackcompose.inventory.ui.InventoryListViewModel


/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for ItemEditViewModel
        initializer {
            InventoryItemEditViewModel(
                this.createSavedStateHandle()
            )
        }
        // Initializer for ItemEntryViewModel
        initializer {
            InventoryItemEntryViewModel(codeLabsApplication().container.itemsRepository)
        }

        // Initializer for ItemDetailsViewModel
        initializer {
            InventoryItemDetailsViewModel(
                codeLabsApplication().container.itemsRepository
            )
        }


        initializer {
            InventoryListViewModel(codeLabsApplication().container.itemsRepository)
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [InventoryApplication].
 */
fun CreationExtras.codeLabsApplication(): CodeLabsApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as CodeLabsApplication)