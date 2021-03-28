package com.example.gok.source.remote

import com.example.gok.data.model.MainResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface Api {
    @GET("products")
    suspend fun getProducts(): MainResponse
}