package org.kotlinacademy.backend.usecases

import org.kotlinacademy.backend.Config
import org.kotlinacademy.backend.repositories.db.DatabaseRepository
import org.kotlinacademy.backend.repositories.email.EmailRepository
import org.kotlinacademy.backend.repositories.network.dto.NotificationResult
import org.kotlinacademy.data.Feedback

suspend fun sendEmailWithInfoAboutFeedback(feedback: Feedback, emailRepository: EmailRepository, databaseRepository: DatabaseRepository) {
    val adminEmail = Config.adminEmail ?: return
    val feedbackTo = feedback.newsId?.let { databaseRepository.getNews(it) }?.title ?: "Kotlin Academy"
    emailRepository.sendEmail(
            to = adminEmail,
            title = "New feedback",
            message = """
                |New feedback to $feedbackTo
                |Rating: ${feedback.rating}
                |Comment:
                |${feedback.comment}
                |
                |Suggestions:
                |${feedback.suggestions}
            """.trimMargin()
    )
}

suspend fun sendEmailWithNotificationResult(result: NotificationResult, emailRepository: EmailRepository) {
    val adminEmail = Config.adminEmail ?: return
    emailRepository.sendEmail(
            to = adminEmail,
            title = "Notification report",
            message = """
                |Success: ${result.success}
                |Failure: ${result.failure}
            """.trimMargin()
    )
}

suspend fun sendMailing(title: String, message: String, emailRepository: EmailRepository, databaseRepository: DatabaseRepository) {
    val emailSubscriptions = databaseRepository.getEmailSubscriptions()
    emailSubscriptions.forEach { (email) ->
        emailRepository.sendEmail(email, title, message)
    }
}

suspend fun sendSubscriptionAddedEmail(to: String, emailRepository: EmailRepository) {
    sendSubscriptionEmail(to, "Thank you", "Thank you for subscribing Kotlin Academy newsletter :)", emailRepository)
}

suspend fun sendSubscriptionEmail(to: String, title: String, content: String, emailRepository: EmailRepository) {
    emailRepository.sendEmail(to, title, content + "\n ")
}