package com.app.mvi.ui.main

import com.app.mvi.model.User

sealed class MainStates {

    object Idle: MainStates()
    object Loading : MainStates()
    data class Users(val user: List<User>) : MainStates()
    data class Error(val error: String?) : MainStates()

}

