package com.voidhash.tutorial_2

import com.voidhash.util.LogUtils.threadLogInfo
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

suspend fun main() {

    // Define a coroutine scope
    val coroutineScope = CoroutineScope(Dispatchers.Default)

    val taskDurationSeconds = 5

    // Everything in '.launch{}' will be executed inside a coroutine
    val taskJob = coroutineScope.launch {
        val taskResult = backgroundTask(taskDurationSeconds)
        threadLogInfo("$taskResult")
    }

    val timerJob = coroutineScope.launch {
        printTaskTime(taskDurationSeconds)
    }

    /* Suspends current coroutine until all given jobs are complete
     * The main function will only finish when all jobs finished.
     * Use joinAll in the end of you logic (suspend function)
     */
    joinAll(taskJob, timerJob)
}

private suspend fun backgroundTask(taskDurationSeconds: Int): Long {
    threadLogInfo("background task started")

    val stopTimeNano = System.nanoTime() + taskDurationSeconds * 1_000_000_000L

    var iterationsCount: Long = 0
    while (System.nanoTime() < stopTimeNano && coroutineContext.isActive) {
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
