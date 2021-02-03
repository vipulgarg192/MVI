package com.app.mvi.network

import com.app.mvi.model.User

interface ApiHelper {

    suspend fun getUser(): List<User>
}