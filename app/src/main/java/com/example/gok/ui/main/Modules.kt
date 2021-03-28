package com.example.gok.ui.main

import com.example.gok.data.repository.MainRepository
import com.example.gok.data.repository.MainRepositoryImpl
import com.example.gok.source.remote.Api
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val mainViewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
}

val mainRepositoryModule = module {
    single<MainRepository> { MainRepositoryImpl(get()) }
}

val apiModule = module {
    fun provideUseApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    single { provideUseApi(get()) }
}

val retrofitModule = module {
    fun provideRetrofit(): Retrofit {
        val BASE_URL = "https://7hgi9vtkdc.execute-api.sa-east-1.amazonaws.com/sandbox/"

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { provideRetrofit() }
}