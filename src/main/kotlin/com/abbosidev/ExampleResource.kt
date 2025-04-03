package com.abbosidev

import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path("/api/v1")
class ExampleResource(
    private val exampleService: ExampleService,
) {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun hello() = "Hello from Quarkus REST"

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    suspend fun createUser(user: UserDto) = exampleService.createUser(user)
}

data class UserDto(
    val firstname: String,
    val lastname: String,
)