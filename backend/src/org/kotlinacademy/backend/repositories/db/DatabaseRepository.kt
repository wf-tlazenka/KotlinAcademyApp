package org.kotlinacademy.backend.repositories.db

import org.kotlinacademy.common.Provider
import org.kotlinacademy.data.*

interface DatabaseRepository {

    suspend fun getNews(): List<News>
    suspend fun getNews(id: Int): News
    suspend fun addNews(news: News)
    suspend fun updateNews(id: Int, news: News)
    suspend fun getFeedback(): List<Feedback>
    suspend fun addFeedback(feedback: Feedback)
    suspend fun getAllTokens(): List<FirebaseTokenData>
    suspend fun addToken(token: String, tokenType: FirebaseTokenType)
    suspend fun getEmailSubscriptions(): List<Subscription>
    suspend fun addEmailSubscription(email: String): Subscription
    suspend fun removeEmailSubscription(key: String)

    companion object: Provider<DatabaseRepository>() {
        override fun create(): DatabaseRepository = Database
    }
}