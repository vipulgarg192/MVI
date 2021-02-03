package com.app.mvi.ui.main

sealed class MainIntent {

    object FetchUser : MainIntent()
}