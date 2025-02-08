package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

fun <T>LiveData<T>.getOrWaitValue(time: Long = 2, timeUnit: TimeUnit = TimeUnit.SECONDS): T {

    var data: T? = null
    val latch = CountDownLatch(/* count= */ 1)

    val observer = object : Observer<T> {

        override fun onChanged(t: T) {
            data = t
            latch.countDown()
            removeObserver(this)
        }
    }

    observeForever(observer)

    try {

        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("Latch Timeout occurred")
        }
    } finally {
        removeObserver(observer)
    }

    return data as T
}