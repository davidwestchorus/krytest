package se.test.services

import io.ktor.client.request.*
import se.test.domain.Service
import se.test.domain.ServiceResponseDTO
import se.test.utils.getHttpClient

suspend fun pollServices() {
    fetchAllServices().forEach { service ->
        val serviceResponse = pollService(service)
        updateServiceResponse(service, serviceResponse)
    }
}

suspend fun pollService(service: Service): ServiceResponseDTO {
    //TODO: This would of course be the real service URL, now calling a "mocked" endpoint
    return getHttpClient().get("http://localhost:8080/serviceCheck?url=${service.url}")
}