package org.kotlinacademy.respositories

import org.kotlinacademy.common.Provider
import org.kotlinacademy.data.FirebaseTokenType

interface SubscriptionRepository {

    suspend fun subscribe(email: String)
    suspend fun unsubscribe(key: String)

    companion object : Provider<SubscriptionRepository>() {
        override fun create(): SubscriptionRepository = RepositoriesProvider.getSubscriptionRepository()
    }
}