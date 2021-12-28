package com.killua.composetraining.data.services

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*

sealed class NetworkHandler<T>(
    val data: T? = null,
    val message: String? = null,
) {
    class Success<T>(data: T?) : NetworkHandler<T>(data)
    class Error<T>(message: String, data: T? = null) : NetworkHandler<T>(data, message)
    object Handler {
        suspend inline fun <reified T> safeApiCall(
            apiCall: HttpClient,
            url: String,
            method: HttpMethod,
            id: String? = null,
            body: Any? = null
        ): NetworkHandler<T> {
            return try {
                val req = apiCall.request<T> {
                    this.url(url)
                    if (id != null) this.parameter("id", id)
                    this.method = method
                    if (body != null) this.body = body

                }
                if (req != null)
                    Success(req)
                else throw Exception("is Null")
            } catch (e: Exception) {
                error(e.toCustomExceptions())
            }

        }

        fun Exception.toCustomExceptions(): String = when (this) {
            is ServerResponseException -> Failure.HttpErrorInternalServerError(this).message
            is ClientRequestException ->
                when (this.response.status.value) {
                    400 -> Failure.HttpErrorBadRequest(this).message
                    401 -> Failure.HttpErrorUnauthorized(this).message
                    403 -> Failure.HttpErrorForbidden(this).message
                    404 -> Failure.HttpErrorNotFound(this).message
                    else -> Failure.GenericError(this).message
                }
            is RedirectResponseException -> Failure.HttpError(this).message
            else -> Failure.GenericError(this).message
        }
    }

    fun <T> error(errorMessage: String): NetworkHandler<T> =
        Error(errorMessage)


    sealed class Failure(val message: String) {
        class HttpErrorInternalServerError(serverResponseException: ServerResponseException) :
            Failure(serverResponseException.message.toString())

        class HttpErrorBadRequest(serverResponseException: ClientRequestException) :
            Failure(serverResponseException.message.toString())

        class HttpErrorUnauthorized(serverResponseException: ClientRequestException) :
            Failure(serverResponseException.message.toString())

        class HttpErrorForbidden(serverResponseException: ClientRequestException) :
            Failure(serverResponseException.message.toString())

        class HttpErrorNotFound(serverResponseException: ClientRequestException) :
            Failure(serverResponseException.message.toString())

        class HttpError(serverResponseException: RedirectResponseException) :
            Failure(serverResponseException.message.toString())

        class GenericError(serverResponseException: Exception) :
            Failure(serverResponseException.message.toString())

    }


}