package com.pocket.codelabsjetpackcompose.marsphoto.data

import com.pocket.codelabsjetpackcompose.base.network.MarsApiService
import com.pocket.codelabsjetpackcompose.marsphoto.model.MarsPhoto

class NetworkMarsPhotosRepository(private val marsApiService: MarsApiService) :
    MarsPhotosRepository {
    override suspend fun getMarsPhotos(): List<MarsPhoto> = marsApiService.getPhotos()

}