## Coroutine Memory Sharing


In concurrent programming is common issues related to memory sharing. The following 
[example](https://github.com/VoidHash/kotlin-coroutines/blob/master/src/main/kotlin/tutorial_7/Main7.kt) 
we have 1000 coroutines accessing the same variable in memory. 
It may happen that two coroutines simultaneously access the *counter* variable, 
incrementing it at the same time, thus generating an inconsistency in the final result.

```kotlin
var counter01 = 0
withContext(Dispatchers.Default) {
    // launch 1000 coroutines at once
    repeat(1000) {
        launch {
            delay(100)
            // increment counter variable
            counter01++
        }
    }
}
threadLogInfo("Counter value: $counter01")
```

![tutorial_07](https://github.com/VoidHash/kotlin-coroutines/assets/8929413/2e7c3a31-0bc3-4a13-8e35-83c777eccdd9)



We can solve this problem by using *Async* coroutines, to do so, we declare a list 
of *Deferred*, which are Jobs that return a value, and after executing all coroutines, 
we add up all the returned values. Thus solving the memory sharing problem in our 
[exemple](https://github.com/VoidHash/kotlin-coroutines/blob/master/src/main/kotlin/tutorial_7/Main7.kt).

````kotlin
var counter02 = 0
val deferredList = mutableListOf<Deferred<Int>>()
withContext(Dispatchers.Default) {
    repeat(1000) {
        deferredList.add(
            async {
                delay(100)
                1
            }
        )
    }
    for (deferred in deferredList) {
        counter02 += deferred.await()
    }
}
threadLogInfo("Counter value: $counter02")
````

![tutorial_07_02](https://github.com/VoidHash/kotlin-coroutines/assets/8929413/ec4220e3-4afc-41f8-9446-9a7cfccff3e9)


