## Creating coroutines

In order to create a coroutine we need to define a scope and a dispatcher
```kotlin
val coroutineScope = CoroutineScope(Dispatchers.Default)
```

Thus, we can use our *coroutineScope* to run coroutine. Everything inside *.launch{}* will run in a coroutine
```kotlin
//Both Jobs will be executed at same time
val myJob01 = coroutineScope.launch {
    //Your code to be run in a coroutine
}
val myJob02 = coroutineScope.launch {
    //Your code to be run in a coroutine
}
```

To print our jobs running in a coroutine we must suspend your Main function util all jobs done.
We can do that using *joinAll()* in the end of our code:
```kotlin
joinAll(myJob01, myJob02)

// We can do this individually
// myJob01.join()
// myJob02.join()
```

In this [example](https://github.com/VoidHash/kotlin-coroutines/blob/master/src/main/kotlin/tutorial_2/Main2.kt), we define 2 function to run concurrently. Checking the logs we notice that both functions start and 
finish at same time

![tutorial_02_40](https://github.com/VoidHash/kotlin-coroutines/assets/8929413/6f2d9fa8-1694-47ca-a102-f85ca452f120)
