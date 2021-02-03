package com.app.mvi.repository

import com.app.mvi.network.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getUser() = apiHelper.getUser()

}