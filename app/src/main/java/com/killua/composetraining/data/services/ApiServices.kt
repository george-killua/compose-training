package com.killua.composetraining.data.services

import com.killua.composetraining.data.models.Users


interface ApiServices {
    suspend fun getAllUsers(): NetworkHandler<List<Users>>
    suspend fun getUser(id: String): NetworkHandler<Users>
    suspend fun postUser(user: Users): NetworkHandler<Users>
    suspend fun updateUser(user: Users): NetworkHandler<Users>
    suspend fun deleteUser(id: String): NetworkHandler<Users>
}


