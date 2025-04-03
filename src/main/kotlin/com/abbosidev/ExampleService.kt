package com.abbosidev

import io.quarkus.logging.Log
import io.smallrye.mutiny.Uni
import io.smallrye.mutiny.coroutines.awaitSuspending
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient
import software.amazon.awssdk.enhanced.dynamodb.TableSchema

@ApplicationScoped
class ExampleService
@Inject constructor(
    private val dynamoEnhancedClient: DynamoDbEnhancedAsyncClient,
) {
    var userTable: DynamoDbAsyncTable<User> =
        dynamoEnhancedClient.table(
            "user_table",
            TableSchema.fromClass(User::class.java),
        )

    suspend fun createUser(user: UserDto): Void? =
        try {
            Uni.createFrom().completionStage {
                userTable.putItem(User(user.firstname, user.lastname))
            }.awaitSuspending()
        } catch (e: Exception) {
            Log.warn("Error during put item: ${e.message}")
            null
        }
}