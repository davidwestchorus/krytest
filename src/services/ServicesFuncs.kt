package se.test.services

import se.test.domain.ServiceDTO

fun addService(serviceDTO: ServiceDTO): ServiceDTO {
    return saveService(serviceDTO).toDTO()
}

fun getAllServices(): List<ServiceDTO> {
    return fetchAllServices().map { it.toDTO() }.sortedByDescending { it.latestUpdate }
}