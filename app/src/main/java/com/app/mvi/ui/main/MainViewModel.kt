package com.app.mvi.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.mvi.repository.MainRepository
import com.app.mvi.ui.main.MainIntent
import com.app.mvi.ui.main.MainStates
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val repo: MainRepository): ViewModel() {

    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)

    private val _state = MutableStateFlow<MainStates>(MainStates.Idle)
    val state: StateFlow<MainStates>
        get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {

        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when(it){
                    is MainIntent.FetchUser -> fetchUser()
                }
            }
        }
    }


    private fun fetchUser(){
        viewModelScope.launch {
            _state.value = MainStates.Idle
            _state.value = try {
                MainStates.Users(repo.getUser())
            }catch (e: Exception){
                MainStates.Error(e.localizedMessage)
            }
        }
    }

}