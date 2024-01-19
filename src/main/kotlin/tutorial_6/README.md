## Coroutine hierarchic

To understand the hierarchic of a coroutine, we must understand what is a Job.

A Job is an interface that represents a unit of asynchronous work, often associated 
with the use of coroutines. Therefore, a Job is basically an asynchronous task that 
can be launched to run in the background, and can be monitored, canceled or completed.
When a coroutine is launched, it returns a Job. And this Job represents the execution 
of this coroutine.

So, a coroutine is used to be associated to a job, if a job is canceled, all coroutine
associated to then is canceled too.

To declare a Job, you just need:
```kotlin
// Define a Job
val scopeJob = Job()
// Create a specific coroutine scope with Job created
val scope = CoroutineScope(scopeJob + Dispatchers.IO + CoroutineName("IO Coroutine"))
```
Thus, we can launch our coroutine:
````kotlin
scope.launch {
    // Your logic here
}

````
You can change a coroutine scope inside a *.launch{ }* block if you need to do so:
```kotlin
scope.launch {
        delay(150)
        // Change the current context of our coroutine
        withContext(Dispatchers.IO + CoroutineName("New Name")) {
            delay(150)
        }
    }
```

Following the job hierarchic, the *withContext* coroutine will run inside the *scope*
coroutine. 

In this [exemple](https://github.com/VoidHash/kotlin-coroutines/blob/master/src/main/kotlin/tutorial_6/Main6.kt), we implement an external job, and three coroutine 
running inside it. We use *withContext()* to change the context of one of then. 
When we print the Job hierarchic is easy to know how the coroutines is 
organized inside it. 

![tutorial_06_40](https://github.com/VoidHash/kotlin-coroutines/assets/8929413/ee35073f-fed4-4967-b503-994b6c927b08)

