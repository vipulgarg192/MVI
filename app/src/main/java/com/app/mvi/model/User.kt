package com.app.mvi.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class User(

    @SerializedName("id")
    @Expose
    val id : Int = 0,
    @SerializedName("name")
    @Expose
    val name: String = "",
    @SerializedName("email")
    @Expose
    val email : String = "",
    @SerializedName("avatar")
    @Expose
    val avatar: String = ""


)