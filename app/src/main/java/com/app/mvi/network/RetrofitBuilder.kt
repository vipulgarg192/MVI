package com.app.mvi.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitBuilder {

    private const val BASE_URL = "https://5e510330f2c0d300147c034c.mockapi.io/"

    val client = OkHttpClient.Builder().apply {

        addInterceptor(HttpLoggingInterceptor(
            HttpLoggingInterceptor.Logger { message ->
                println("LOG-APP: $message")
            }).apply {
            level= HttpLoggingInterceptor.Level.BODY
        })

        addNetworkInterceptor(HttpLoggingInterceptor(
            HttpLoggingInterceptor.Logger { message ->
                println("LOG-NET: $message")
            }).apply {
            level= HttpLoggingInterceptor.Level.BODY
        })
    }.build()

    private
    fun getRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val apiServices : ApiServices = getRetrofit().create(ApiServices::class.java)


}