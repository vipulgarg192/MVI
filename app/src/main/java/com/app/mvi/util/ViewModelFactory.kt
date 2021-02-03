package com.app.mvi.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.mvi.network.ApiHelper
import com.app.mvi.repository.MainRepository
import com.app.mvi.ui.main.MainViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val apiHelper: ApiHelper)
    : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}