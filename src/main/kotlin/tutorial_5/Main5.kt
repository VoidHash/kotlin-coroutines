package com.voidhash.tutorial_5

import com.voidhash.util.LogUtils.threadLogInfo
import kotlinx.coroutines.*

suspend fun main() {
    val coroutineScope = CoroutineScope(Dispatchers.Default)
    val taskDurationSeconds = 5

    threadLogInfo("Init")

    //Define an async coroutine, returning a Long
    val taskDeferred = coroutineScope.async<Long> {
        val taskResult = backgroundTask(taskDurationSeconds)
        taskResult
    }

    val timerJob = coroutineScope.launch {
        printTaskTime(taskDurationSeconds)
    }

    /* Awaits for completion of this value without blocking a thread and resumes
     * when deferred computation is complete, returning the resulting value or throwing
     * the corresponding exception if the deferred was cancelled.
     */
    val result = taskDeferred.await()
    timerJob.join()

    // Print the async coroutine value result
    threadLogInfo("result: $result")
}

private fun backgroundTask(taskDurationSeconds: Int): Long {
    threadLogInfo("background task started")

    val stopTimeNano = System.nanoTime() + taskDurationSeconds * 1_000_000_000L

    var iterationsCount: Long = 0
    while (System.nanoTime() < stopTimeNano) {
        iterationsCount++
    }

    threadLogInfo("background task completed")

    return iterationsCount
}

private suspend fun printTaskTime(remainingTimeSeconds: Int) {
    threadLogInfo("$remainingTimeSeconds seconds to finish the task")

    if (remainingTimeSeconds > 0) {
        delay(1000)
        printTaskTime(remainingTimeSeconds - 1)
    } else {
        threadLogInfo("task finished!")
    }
}