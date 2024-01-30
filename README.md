# Kotlin Coroutines

Asynchronous or non-blocking programming is an important part of the development landscape. When creating server-side, desktop, or mobile applications, it's important to provide an experience that is not only fluid from the user's perspective, but also scalable when needed. Kotlin solves this problem in a flexible way by providing coroutine support at the language level and delegating most of the functionality to libraries. 

### Tutoriais

This tutoriais is part of my studies about kotlin coroutines. My motivation is to build a knowledge base about this subject in order to help anyone who wishes to delve deeper into this topic.

1. [Sequential code](https://github.com/VoidHash/kotlin-coroutines/tree/master/src/main/kotlin/tutorial_1)
2. [Creating coroutines](https://github.com/VoidHash/kotlin-coroutines/blob/master/src/main/kotlin/tutorial_2)
3. [Coroutine cancellation](https://github.com/VoidHash/kotlin-coroutines/blob/master/src/main/kotlin/tutorial_3)
4. [Non cancellable coroutine](https://github.com/VoidHash/kotlin-coroutines/blob/master/src/main/kotlin/tutorial_4)
5. [Async coroutines](https://github.com/VoidHash/kotlin-coroutines/tree/master/src/main/kotlin/tutorial_5)
6. [Coroutine hierarchic](https://github.com/VoidHash/kotlin-coroutines/blob/master/src/main/kotlin/tutorial_6)
7. [Memory sharing](https://github.com/VoidHash/kotlin-coroutines/tree/master/src/main/kotlin/tutorial_7)
8. [Supervisor job](https://github.com/VoidHash/kotlin-coroutines/tree/master/src/main/kotlin/tutorial_8)

### **Important concepts**

For a better understanding about coroutines in Kotlin, we must first learn more about some important concepts on this subject:

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

![dispatcher_table_50](https://github.com/VoidHash/kotlin-coroutines/assets/8929413/11342975-d1c4-4fab-b69d-8cce09ffd56d)
<p></p>

- ### Scopes

> A coroutine **Scope** is an interface that provides a structure to manage the coroutines execution. It is used to launch coroutines and manage their lifecycles. It also defines a context for the coroutines, which includes information about the execution hierarchy, cancellation, and other associated settings. When a Scope is canceled all coroutines associated with it are canceled together.

![lifecycle_coroutines_50](https://github.com/VoidHash/kotlin-coroutines/assets/8929413/e6ac126d-6a68-497d-a19a-787a2ad8aa26)
<p></p>

- ### Jobs

> A **Job** is an interface that represents a unit of asynchronous work, often associated with the use of coroutines. Therefore, a **Job** is basically an asynchronous task that can be launched to run in the background, and can be monitored, canceled or completed. When a coroutine is launched, it returns a Job. And this Job represents the execution of this coroutine.

![coroutine_structure_50](https://github.com/VoidHash/kotlin-coroutines/assets/8929413/d3322b13-4e6e-4a7b-b7dc-37b1b7529519)
<p></p>

## Advantages of Coroutines over Threads

1. **Structured Concurrency**: Coroutines provide a structured way to manage concurrency, making it easier to handle complex asynchronous operations. They allow developers to write sequential-looking code while still achieving concurrency.<p></p>
2. **Lightweight**: Coroutines are lightweight compared to threads, as they don’t require creating and managing additional system resources. This makes coroutines more efficient in terms of memory usage.<p></p>
3. **Suspend and Resume**: Coroutines allow for suspending and resuming execution at specific points, making it easier to handle long-running tasks without blocking the main thread. This helps in keeping the UI responsive and improves the overall user experience.<p></p>
4. **Exception Handling**: Coroutines provide built-in exception handling mechanisms, making it easier to handle and propagate exceptions within the coroutine context.<p></p>
5. **Independence**: Unlike threads for coroutines, the application by default does not wait for it to finish the execution.<p></p>

Both threads and coroutines have their place in Kotlin Android development when it comes to managing concurrency. Threads are a more traditional approach, while coroutines provide a more modern and structured way to handle asynchronous operations. Coroutines offer advantages such as structured concurrency, lightweight execution, and better exception handling. However, it’s important to choose the right tool for the job based on the specific requirements of your application.

## Easy mode

```kotlin
suspend fun main() {

    val scopeJob = Job()
    val scope = CoroutineScope(scopeJob + Dispatchers.IO + CoroutineName("external scope"))

    val job = scope.launch(Dispatchers.Default + CoroutineName("internal coroutine")) {
        delay(150)
    }

    job.join()
}
```


