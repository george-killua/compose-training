package com.killua.composetraining.data

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*

object StaticThings {
    private const val url = "https://61cab299194ffe00177888a0.mockapi.io/api/v1/"
    const val URL_USER = "${StaticThings.url}/users"
    private var timeoutConnection = 60_000


    val ktorClientBuilder = HttpClient(Android) {

        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
        engine {
            connectTimeout = timeoutConnection
            socketTimeout = timeoutConnection
        }

        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
    }
}