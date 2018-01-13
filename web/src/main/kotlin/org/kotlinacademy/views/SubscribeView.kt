package org.kotlinacademy.views

import react.RBuilder
import react.ReactElement
import react.dom.div

fun RBuilder.unsubscribedSuccessfullyView(): ReactElement? = div { }

fun RBuilder.unsubscribeView(onUnsubscribeClick: ()->Unit): ReactElement? = div { }

fun RBuilder.resubscribedSuccessfullyView(): ReactElement? = div { }

fun RBuilder.subscribedSuccessfullyView(): ReactElement? = div { }

fun RBuilder.subscribeView(onSubscribed: (String) -> Unit): ReactElement? = div(classes = "list-center") { }