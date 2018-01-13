package org.kotlinacademy.presentation.subscribe

import org.kotlinacademy.common.launchUI
import org.kotlinacademy.presentation.BasePresenter
import org.kotlinacademy.respositories.SubscriptionRepository

class SubscribePresenter(
        private val view: SubscribeView
) : BasePresenter() {

    private val subscriptionRepository by SubscriptionRepository.lazyGet()

    fun onSubscribe(email: String) {
        jobs += launchUI {
            try {
                subscriptionRepository.subscribe(email)
                view.subscribed = true
            } catch (e: Throwable) {
                view.logError(e)
            }
        }
    }

    fun onUnsubscribe(key: String) {
        jobs += launchUI {
            try {
                subscriptionRepository.unsubscribe(key)
            } catch (e: Throwable) {
                view.logError(e)
            }
        }
    }
}