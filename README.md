# Kotlin Coroutines


### **Important concepts**

To have a better understanding about coroutines in Kotlin, we must first learn more about some important concepts on this subject:

- ### Concurrency

> Concurrency in software development refers to the simultaneous execution of multiple tasks or processes, where operations can occur overlapping in time, allowing several parts of the code to be executed at the same time, thus making the best use of available resources, improving the efficiency and performance of developed software.

- ### Thread

> In computer programming, a thread is the smallest unit of execution within a process. A process can have one or multiple threads, and each thread executes independently, sharing the process's resources and address space. Threads allow a program to perform tasks concurrently, which can improve an application's performance and responsiveness. Each thread has its own program counter, registers, and execution stack, but shares resources such as global variables and open files with other threads in the same process.

- ### Suspending function

> Suspending Function are functions that can be set in a suspended state without blocking the main thread. These functions are used in conjunction with coroutines to perform asynchronous operations more efficiently. Unlike synchronous functions, suspending functions can pause and resume execution without blocking the thread, allowing other tasks to run while waiting for asynchronous operations, such as network calls or database access. This contributes to more concise and responsive code in asynchronous applications.

- ### Coroutines

> A Coroutine is a **concurrent** programming framework that allows for asynchronous and efficient code execution. It is used together with the **suspending functions** to facilitate concurrency without need multiple **threads**. Coroutines allow parts of code to be suspended and resumed, allowing asynchronous operations without blocking threads. This makes for more concise, readable, and efficient code compared to traditional thread-based approaches. Coroutines are an essential part of the asynchronous approach in developing solutions where multiple tasks need to be executed simultaneously and independently.

- ### Dispatches

> The term **Dispatcher** refers to a component used to determine the execution context in which a coroutine will run. Dispatchers are essential for efficiently managing threads and allocating asynchronous tasks across different contexts.

![dispatcher_table](https://github.com/VoidHash/kotlin-coroutines/assets/8929413/2f7a6310-7db5-4305-be7a-c02bf46c55be)

- ### Scopes

> A coroutine **Scope** is an interface that provides a structure to manage the coroutines execution. It is used to launch coroutines and manage their lifecycles. It also defines a context for the coroutines, which includes information about the execution hierarchy, cancellation, and other associated settings. When a Scope is canceled all coroutines associated with it are canceled together.

![lifecycle_coroutines](https://github.com/VoidHash/kotlin-coroutines/assets/8929413/dcb6dc92-6695-4713-86f1-c6241889fc53)

- ### Jobs

> A **Job** is an interface that represents a unit of asynchronous work, often associated with the use of coroutines. Therefore, a **Job** is basically an asynchronous task that can be launched to run in the background, and can be monitored, canceled or completed. When a coroutine is launched, it returns a Job. And this Job represents the execution of this coroutine.

![sample](https://github.com/VoidHash/kotlin-coroutines/assets/8929413/1c0e6d9e-96e7-493f-8d67-15c5174e71d5)

<br>

## Easy mode

```kotlin
suspend fun main() {

    val scopeJob = Job()
    val scope = CoroutineScope(scopeJob + Dispatchers.IO + CoroutineName("external scope"))
    
    scope.printInfo()

    val job = scope.launch(Dispatchers.Default + CoroutineName("internal coroutine")) {
        this.printInfo()
        delay(150)
    }

    job.join()
}
```


