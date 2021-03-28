package com.example.gok.ui.main

import com.example.gok.data.model.Cash
import com.example.gok.data.model.MainResponse
import com.example.gok.data.model.Product
import com.example.gok.data.model.Spotlight
import com.example.gok.data.repository.MainRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import java.lang.Exception

class MainViewModelTest {

    lateinit var viewModel: MainViewModel
    lateinit var repository: MainRepository

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        repository = mockk()
        viewModel = MainViewModel(repository)

        viewModel.coroutineContext = Dispatchers.Unconfined + SupervisorJob()
    }

    @Test
    fun `doMainRequest - Verifica sucesso da requisicao`() = runBlocking {
        coEvery { repository.doMainRequest() } returns getMainResponse()

        viewModel.getMainResponse()

        Assert.assertEquals(viewModel.states.value, MainViewState.ShowResponse(getMainResponse()))
    }

    @Test
    fun `doMainRequest - Verifica falha da requisicao`() = runBlocking {
        val exception: Exception = mockk()

        coEvery { repository.doMainRequest() } throws exception

        viewModel.getMainResponse()

        Assert.assertEquals(viewModel.states.value, MainViewState.ShowError(ERROR))
    }

    private fun getMainResponse(): MainResponse = MainResponse(
        arrayListOf(getSpotLight()),
        arrayListOf(getProduct()),
        getCash()
    )

    private fun getSpotLight(): Spotlight = Spotlight(
            "nameSpotlight", "bannerUrlSpotlight", "descriptionSpotlight"
    )

    private fun getProduct(): Product = Product(
        "nameProduct", "imagelUrlProduct", "descriptionSpotlight"
    )

    private fun getCash(): Cash = Cash(
        "titleCash", "bannerUrlCash", "descriptionCash"
    )

    companion object {
        const val ERROR = "erro"
    }
}