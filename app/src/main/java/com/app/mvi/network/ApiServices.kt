package com.app.mvi.network

import com.app.mvi.model.User
import retrofit2.http.GET

interface ApiServices {

    @GET("users")
    suspend fun getUser(): List<User>

}