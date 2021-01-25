package se.test.domain

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable

object Services: IntIdTable("services") {
    val name = varchar("name", length = 500)
    val url = varchar("url", length = 500)
    val upResponse = enumerationByName("up_response", 20, Service.UpResponse::class.java).nullable()
    val added = long("added")
    val latestUpdate = long("latest_update")
}

class Service(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<Service>(Services)

    var name by Services.name
    var url by Services.url
    var upResponse by Services.upResponse
    var added by Services.added
    var latestUpdate by Services.latestUpdate
    enum class UpResponse { OK, FAIL }

    fun toDTO(): ServiceDTO {
        return ServiceDTO(
            name, url, upResponse, added, latestUpdate
        )
    }
}