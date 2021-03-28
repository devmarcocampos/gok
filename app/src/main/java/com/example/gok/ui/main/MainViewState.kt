package com.example.gok.ui.main

import com.example.gok.data.model.MainResponse

sealed class MainViewState {
    data class ShowResponse(val mainResponse: MainResponse) : MainViewState()
    data class ShowError(val error: String) : MainViewState()
}

sealed class LoadingViewState {
    data class ShowLoading(val isLoading: Boolean) : LoadingViewState()
}