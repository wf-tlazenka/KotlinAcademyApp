package org.kotlinacademy.backend.repositories.db

import org.jetbrains.squash.definition.*

object SubscriptionsTable : TableDefinition() {
    val email = text("email")
    val key = varchar("key", 10)
    val creationTime = text("creationtime")
}