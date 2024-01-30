package com.voidhash.tutorial_8

import kotlinx.coroutines.*
import kotlin.random.Random

suspend fun main() {

    val coroutineExceptionHandler = CoroutineExceptionHandler { _, e ->
        println("Caught exception: $e")
    }

    // When we throw an exception at badJob coroutine, all jobs will be canceled
    jobCoroutine(coroutineExceptionHandler)

    // Because SupervisorJob, only badJob coroutine will be canceled
    supervisorJobCoroutine(coroutineExceptionHandler)
}

suspend fun jobCoroutine(coroutineExceptionHandler: CoroutineExceptionHandler) {
    println("Init === Job === Example")
    val job = Job()
    val scope = CoroutineScope(job + coroutineExceptionHandler + Dispatchers.Default)

    val goodJob = scope.launch {
        delay(200)
        println("Result: " + Random.nextInt(0, 100) * Random.nextInt(0, 100))
    }

    val badJob = scope.launch {
        delay(100)
        throw Exception()
    }

    joinAll(goodJob, badJob)

    println("job: $job")
    println("goodJob: $goodJob")
    println("badJob: $badJob")
    println()
}

suspend fun supervisorJobCoroutine(coroutineExceptionHandler: CoroutineExceptionHandler) {
    println("Init == SupervisorJob == Example")
    val supervisorJob = SupervisorJob()
    val scope = CoroutineScope(supervisorJob + coroutineExceptionHandler + Dispatchers.Default)

    val goodJob = scope.launch {
        delay(200)
        println("Result: " + Random.nextInt(0, 100) * Random.nextInt(0, 100))
    }

    val badJob = scope.launch {
        delay(100)
        throw Exception()
    }

    joinAll(goodJob, badJob)

    println("supervisorJob: $supervisorJob")
    println("goodJob: $goodJob")
    println("badJob: $badJob")
    println()
}