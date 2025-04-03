package com.abbosidev

import software.amazon.awssdk.enhanced.dynamodb.mapper.UpdateBehavior
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondarySortKey
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbUpdateBehavior
import java.time.Instant
import java.time.Instant.now
import java.util.UUID

@DynamoDbBean
class User() {
    constructor(firstname: String, lastname: String) : this() {
        this.firstname = firstname
        this.lastname = lastname
    }

    @get:DynamoDbPartitionKey
    var id: UUID = randomUUIDv7()

    @get:DynamoDbUpdateBehavior(UpdateBehavior.WRITE_IF_NOT_EXISTS)
    @get:DynamoDbSecondarySortKey(indexNames = ["createdAt-index"])
    var createdAt: Instant = now()

    lateinit var firstname: String
    lateinit var lastname: String
}