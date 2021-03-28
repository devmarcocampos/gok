package com.example.gok.data.repository

import com.example.gok.data.model.MainResponse
import com.example.gok.source.remote.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepositoryImpl (
    private val api: Api
) : MainRepository {

    override suspend fun doMainRequest(): MainResponse =
        withContext(Dispatchers.IO) {
            api.getProducts()
        }

}