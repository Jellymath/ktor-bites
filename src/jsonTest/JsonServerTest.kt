package com.example.jsonTest

import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    val server = embeddedServer(Netty, 8080) {
        install(ContentNegotiation) {
            jackson()
        }

        routing {
            get("/v1") {
                call.respond(listOf(
                    Model("firstModel", listOf(Item("key11", "value11"), Item("key12", "value12"))),
                    Model("lastModel", listOf(Item("key21", "value21"), Item("key22", "value22")))
                ))
            }
        }
    }
    server.start(wait = true)
}