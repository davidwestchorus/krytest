package se.test

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.client.*
import io.ktor.client.engine.jetty.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.routing.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import se.test.routes.serviceRoutes
import se.test.services.initDataBase
import se.test.services.pollServices

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }
    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Post)
        method(HttpMethod.Get)
        header(HttpHeaders.XForwardedProto)
        header(HttpHeaders.ContentType)
        anyHost()
        host("localhost")
    }

    routing {
        serviceRoutes()
    }

    initDataBase()

    launch {
        while(true) {
            //Poll services every 5 seconds
            delay(5000)
            pollServices()
        }
    }
}
