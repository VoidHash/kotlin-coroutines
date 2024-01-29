package com.voidhash.tutorial_7

import com.voidhash.util.LogUtils.threadLogInfo
import kotlinx.coroutines.*

suspend fun main() {

    var counter01 = 0
    withContext(Dispatchers.Default) {
        repeat(1000) {
            launch {
                delay(100)
                counter01++
            }
        }
    }

    threadLogInfo("=> counter01 value: $counter01")

    var counter02 = 0
    // Deferred value is a non-blocking cancellable future — it is a Job with a result.
    val deferredList = mutableListOf<Deferred<Int>>()
    withContext(Dispatchers.Default) {
        repeat(1000) {
            deferredList.add(
                async {
                    delay(100)
                    1
                }
            )
        }
        for (deferred in deferredList) {
            /* Awaits for completion of this value without blocking a thread and resumes
            when deferred computation is complete, returning the resulting value */
            counter02 += deferred.await()
        }
    }

    threadLogInfo("=> counter02 value: $counter02")

}