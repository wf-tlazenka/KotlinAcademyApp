package org.kotlinacademy.backend.repositories.network.notifications

class PushNotificationData(
        val to: String,
        val notification: NotificationData
)

class NotificationData(
        val title: String,
        val body: String,
        val icon: String,
        val click_action: String
)

class NotificationResult(
        val success: Int,
        val failure: Int
)