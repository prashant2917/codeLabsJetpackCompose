package com.pocket.codelabsjetpackcompose.marsphoto.data

import com.pocket.codelabsjetpackcompose.marsphoto.model.MarsPhoto

sealed interface MarsUiState {
    data class Success(val photos: List<MarsPhoto>) : MarsUiState
    data object Error : MarsUiState
    data object Loading : MarsUiState
}