package org.kotlinacademy.backend.usecases

import org.kotlinacademy.backend.repositories.db.DatabaseRepository
import org.kotlinacademy.backend.repositories.email.EmailRepository

suspend fun addSubscription(email: String, databaseRepository: DatabaseRepository, emailRepository: EmailRepository) {
    databaseRepository.addEmailSubscription(email)
    sendSubscriptionAddedEmail(email, emailRepository)
}

suspend fun removeSubscription(email: String, databaseRepository: DatabaseRepository) {
    databaseRepository.removeEmailSubscription(email)
}