package com.killua.composetraining.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class Users(
    @SerialName("id")
    val id:String,
    @SerialName("createdAt")
    val createdAt:String,
    @SerialName("firstname")
    val firstname:String,
    @SerialName("lastname")
    val lastname:String,
    @SerialName("avatar")
    val avatar:String,
    @SerialName("job")
    val job:String
    )
