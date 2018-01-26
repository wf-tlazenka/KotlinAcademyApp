package org.kotlinacademy.presentation

import kotlinx.coroutines.experimental.Job

abstract class BasePresenter : Presenter {

    protected var jobs: List<Job> = emptyList()

    override fun onDestroy() {
        jobs.forEach { it.cancel() }
    }
}