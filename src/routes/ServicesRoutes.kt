package se.test.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import se.test.domain.Service
import se.test.domain.ServiceDTO
import se.test.domain.ServiceResponseDTO
import se.test.services.addService
import se.test.services.getAllServices

fun Route.serviceRoutes() {
    route("/getServices") {
        get {
            val response = getAllServices()
            call.respond(HttpStatusCode.OK, response)
        }
    }

    route("/addService") {
        post {
            val request = call.receive(ServiceDTO::class)
            call.respond(HttpStatusCode.Created, addService(request))
        }
    }

    //TODO: This is a "mock" and should not exist, in a real situation we would instead call the actual system endpoints
    route("/serviceCheck") {
        get {
            val url = call.request.queryParameters["url"]
            call.respond(ServiceResponseDTO(
                Service.UpResponse.values().toList().shuffled().first())
            )
        }
    }
}