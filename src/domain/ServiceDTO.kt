package se.test.domain

data class ServiceDTO(
    val name: String,
    val url: String,
    val upResponse: Service.UpResponse? = null,
    val added: Long? = null,
    val latestUpdate: Long? = null
)