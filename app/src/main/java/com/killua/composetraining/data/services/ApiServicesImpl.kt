package com.killua.composetraining.data.services

import com.killua.composetraining.data.StaticThings.URL_USER
import com.killua.composetraining.data.models.Users
import com.killua.composetraining.data.services.NetworkHandler.Handler.safeApiCall
import io.ktor.client.*
import io.ktor.http.*

class ApiServicesImpl(private val client: HttpClient) : ApiServices {
    override suspend fun getAllUsers(): NetworkHandler<List<Users>> = safeApiCall(
        client, URL_USER,
        HttpMethod.Get, null
    )


    override suspend fun getUser(id: String): NetworkHandler<Users> =safeApiCall(
    client, URL_USER,
    HttpMethod.Get, id
    )

    override  suspend fun postUser(user: Users): NetworkHandler<Users> =safeApiCall(
        client, URL_USER,
        HttpMethod.Post, body = user
    )

    override suspend fun updateUser(user: Users): NetworkHandler<Users> =safeApiCall(
        client, URL_USER,
        HttpMethod.Put, user.id,user
    )

    override suspend fun deleteUser(id: String): NetworkHandler<Users> =safeApiCall(
        client, URL_USER,
        HttpMethod.Delete, id
    )
}