## Async coroutines

Async coroutines is another way to create coroutine. The main difference is async coroutine
return a defined value. This is useful when we need to a task return something specific to us.

To create an async coroutine just declare *.async<Type>{ }* instead *.launch{ }*
```kotlin
val coroutineScope = CoroutineScope(Dispatchers.Default)

//Define an async coroutine, returning a Long
val taskDeferred = coroutineScope.async<Long> {
    val taskResult = myTaskReturningLong()
    taskResult
}
```

Now, we have to use *.await()* in order to get the return value of our async coroutine.

```kotlin
/* Awaits for completion of this value without blocking a thread and resumes
 * when deferred computation is complete, returning the resulting value or throwing
 * the corresponding exception if the deferred was cancelled.
 */
val result = taskDeferred.await()
println("Async coroutine returns: $result")
```

In this [exemple](https://github.com/VoidHash/kotlin-coroutines/blob/master/src/main/kotlin/tutorial_5/Main5.kt), we define two coroutine, one of then async. When the coroutines
finish, we print the result value expected from our async coroutine.

![tutorial_05_40](https://github.com/VoidHash/kotlin-coroutines/assets/8929413/72159996-9953-4c4f-ba9a-dbca4d02e282)
