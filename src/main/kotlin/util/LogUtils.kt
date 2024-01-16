package com.voidhash.util

object LogUtils {
    fun threadLogInfo(message: String) {
        println("Thread: {${Thread.currentThread().name}; ${Thread.currentThread().threadId()}} Message: $message;")
    }
}