package com.pocket.codelabsjetpackcompose.base.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.pocket.codelabsjetpackcompose.marsphoto.model.MarsPhoto
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import kotlinx.serialization.json.Json
import retrofit2.http.GET

interface MarsApiService {
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}

