package org.kotlinacademy.components

import org.kotlinacademy.common.RouteResultProps
import org.kotlinacademy.presentation.subscribe.SubscribePresenter
import org.kotlinacademy.presentation.subscribe.SubscribeView
import org.kotlinacademy.views.*
import react.RBuilder
import react.RProps
import kotlin.properties.Delegates.observable

class SubscriptionComponent : BaseComponent<RouteResultProps<SubscriptionProps>, SubscriptionComponentState>(), SubscribeView {

    private val presenter by presenter { SubscribePresenter(this) }

    override var subscribed: Boolean by observable(false) { _, _, n ->
        setState { state.subscribed = n }
    }

    override fun RBuilder.render() {
        val inUnsubscribe = props.match.params.key.isNullOrBlank().not()
        val afterSuccessfulSubscribe = state.subscribed == true
        when {
            inUnsubscribe && afterSuccessfulSubscribe -> resubscribedSuccessfullyView()
            afterSuccessfulSubscribe -> subscribedSuccessfullyView()
            inUnsubscribe -> unsubscribeView(
                    onUnsubscribeClick = { presenter.onUnsubscribe(props.match.params.key!!) }
            )
            state.error != null -> errorView(state.error!!)
            else -> subscribeView(presenter::onSubscribe)
        }
    }
}

external interface SubscriptionComponentState : BaseState {
    var subscribed: Boolean?
}

external interface SubscriptionProps : RProps {
    var key: String?
}
