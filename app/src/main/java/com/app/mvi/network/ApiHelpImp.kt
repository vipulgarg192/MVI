package com.app.mvi.network

import com.app.mvi.model.User
import kotlin.reflect.KClass

class ApiHelpImp(private val apiServices: ApiServices)
    : ApiHelper {

    override suspend fun getUser(): List<User> {
        return apiServices.getUser()
    }

}