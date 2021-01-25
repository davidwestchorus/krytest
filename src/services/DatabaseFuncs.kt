package se.test.services

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import se.test.domain.Service
import se.test.domain.ServiceDTO
import se.test.domain.ServiceResponseDTO
import se.test.domain.Services
import java.time.Instant


fun initDataBase() {
    //TODO: Parameterize/external config below, should not be hardcoded
    val url = "jdbc:mysql://krytest:krytest@localhost:3306/krytest?serverTimezone=UTC"
    val driver = "com.mysql.cj.jdbc.Driver"
    Database.connect(url, driver)
    transaction {
        SchemaUtils.create(Services)
    }
}

fun saveService(service: ServiceDTO): Service {
    return transaction {
        Service.new {
            name = service.name
            url = service.url
            added = Instant.now().toEpochMilli()
            latestUpdate = Instant.now().toEpochMilli()
        }
    }
}

fun fetchAllServices(): List<Service> {
    return transaction {
        Service.all().toList()
    }
}

fun updateServiceResponse(service: Service, serviceResponseDTO: ServiceResponseDTO) {
    transaction {
        Services.update({Services.id eq service.id}) {
            it[upResponse] = serviceResponseDTO.upResponse
            it[latestUpdate] = Instant.now().toEpochMilli()
        }
    }
}