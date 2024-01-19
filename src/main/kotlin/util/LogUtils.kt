package com.voidhash.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

object LogUtils {
    fun threadLogInfo(message: String) {
        println("Thread: {${Thread.currentThread().name}; ${Thread.currentThread().threadId()}} Message: $message;")
    }

    fun CoroutineScope.printInfo() {
        println()
        println("CoroutineScope: $this")
        println("CoroutineContext: ${this.coroutineContext}")
        println("Job: ${this.coroutineContext[Job]}")
        println()
    }

    fun Job.printHierarchy(level: Int = 0) {
        val indentation = "....".repeat(level)
        println("$indentation $this")
        for (child in this.children) {
            child.printHierarchy(level + 1)
        }
        if (level == 0) {
            println()
        }
    }

}