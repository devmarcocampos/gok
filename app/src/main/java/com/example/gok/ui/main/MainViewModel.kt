package com.example.gok.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gok.BaseViewModel
import com.example.gok.data.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository) : BaseViewModel() {
    private val _states = MutableLiveData<MainViewState>()
    val states: LiveData<MainViewState>
        get() = _states

    private val _loadingStates = MutableLiveData<LoadingViewState>()
    val loadingStates: LiveData<LoadingViewState>
        get() = _loadingStates

    fun getMainResponse() {
        launch {
            _loadingStates.value = LoadingViewState.ShowLoading(true)
            try {
                val response = mainRepository.doMainRequest()
                println(response)
                _states.value = MainViewState.ShowResponse(response)
            } catch (exception: Exception) {
                println(exception)
                _states.value = MainViewState.ShowError("erro")
            }
            finally {
                _loadingStates.value = LoadingViewState.ShowLoading(false)
            }
        }
    }
}