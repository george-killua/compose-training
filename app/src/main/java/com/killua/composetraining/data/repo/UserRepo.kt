package com.killua.composetraining.data.repo

import com.killua.composetraining.data.models.Users
import com.killua.composetraining.data.services.ApiServices

class UserRepo(private val apiServices: ApiServices) {
    suspend fun getAllUsers() = apiServices.getAllUsers()
    suspend fun getUser(id: String) = apiServices.getUser(id = id)
    suspend fun postUser(user: Users) = apiServices.postUser(user)
    suspend fun updateUser(user: Users) = apiServices.updateUser(user)
    suspend fun deleteUser(id: String) = apiServices.deleteUser(id)
}