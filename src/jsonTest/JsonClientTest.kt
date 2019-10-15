package com.example.jsonTest

import io.ktor.client.*
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.util.KtorExperimentalAPI
import kotlinx.coroutines.*

data class Model(val name: String, val items: List<Item>)
data class Item(val key: String, val value: String)

@KtorExperimentalAPI
fun main() = runBlocking {
    val client = HttpClient(CIO) {
        install(JsonFeature)
    }
    println(client.engine)
    println("Requesting model...")
    val model = client.get<List<Model>>(port = 8080, path = "/v1")
    println(model)
}