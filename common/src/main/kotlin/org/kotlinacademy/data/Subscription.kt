package org.kotlinacademy.data

import kotlinx.serialization.Serializable
import org.kotlinacademy.DateTime

@Serializable
data class Subscription(
        val email: String,
        val key: String,
        val creationTime: DateTime
)