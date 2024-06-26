package com.rezaalamsyah.littlelemon.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.http.ContentType
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json
import io.ktor.serialization.kotlinx.json.json

object ApiClient {
    private const val TIME_OUT = 60_000
    private const val BASE_URL = "https://raw.githubusercontent.com/"

    val client = HttpClient(Android) {
//        defaultRequest {
//            url {
//                protocol = URLProtocol.HTTPS
//                host = BASE_URL
//            }
//        }
        install(ContentNegotiation) {
            json(
                json = Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                },
                contentType = ContentType.Text.Plain
            )

            engine {
                connectTimeout = TIME_OUT
                socketTimeout = TIME_OUT
            }
        }
    }
}

