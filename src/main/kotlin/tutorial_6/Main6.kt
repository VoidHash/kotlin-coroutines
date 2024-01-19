package com.voidhash.tutorial_6

import com.voidhash.util.LogUtils.printHierarchy
import com.voidhash.util.LogUtils.printInfo
import kotlinx.coroutines.*

suspend fun main() {

    // Define a Job
    val scopeJob = Job()
    // Create a specific coroutine scope
    val scope = CoroutineScope(scopeJob + Dispatchers.IO + CoroutineName("=== EXTERNAL-SCOPE ==="))
    scope.printInfo()

    // Launch our first internal coroutine
    val job01 = scope.launch(Dispatchers.Default + CoroutineName("=== INTERNAL-COROUTINE ===")) {
        delay(150)
        this.printInfo()

        // Change the current context of our coroutine
        withContext(Dispatchers.IO + CoroutineName("=== WITH-CONTEXT ===")) {
            this.printInfo()
            delay(150)
            scopeJob.printHierarchy()
        }
    }

    // Other external coroutine
    val job02 = scope.launch(Dispatchers.Default + CoroutineName("=== OTHER-COROUTINE ===")) {
        delay(750)
        this.printInfo()
    }

    job01.join()
    job02.join()
}