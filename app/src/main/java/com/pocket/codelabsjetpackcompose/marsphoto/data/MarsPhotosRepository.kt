package com.pocket.codelabsjetpackcompose.marsphoto.data

import com.pocket.codelabsjetpackcompose.marsphoto.model.MarsPhoto


interface MarsPhotosRepository {
    suspend fun getMarsPhotos(): List<MarsPhoto>
}