package com.example.gok.data.repository

import com.example.gok.data.model.MainResponse

interface MainRepository {
    suspend fun doMainRequest() : MainResponse
}