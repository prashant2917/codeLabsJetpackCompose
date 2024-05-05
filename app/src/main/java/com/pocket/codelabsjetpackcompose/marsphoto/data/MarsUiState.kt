package com.pocket.codelabsjetpackcompose.marsphoto.data

sealed interface MarsUiState {
    data class Success(val photos: String) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}