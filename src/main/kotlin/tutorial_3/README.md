## Coroutine cancellation

Sometimes we need to cancel a job, to do this properly we have to be aware about
our coroutine lifecycle. A coroutine can be canceled when its state is Active.

Thus, when we need to cancel it, we can cancel all coroutine from a scope context at once:
```kotlin
// Create a coroutine scope
val coroutineScope = CoroutineScope(Dispatchers.Default)

val myJob01 = coroutineScope.launch {
    //Your Job here
}

val myJob02 = coroutineScope.launch {
    //Your Job here
}

// Your logic here

coroutineScope.coroutineContext.cancelChildren()
```
Or you can cancel it, individually.
```kotlin
myJob01.cancel()
myJob02.cancel()
```

When you have a task running in background, be sure to call *coroutineContext.ensureActive()* to ensure the coroutine 
context will be stay active.

Note: Some function, like *delay()*, set the coroutine from Active state to Suspend state for amount of time and from
Suspend state to Active state after this.

In this [exemple](https://github.com/VoidHash/kotlin-coroutines/blob/master/src/main/kotlin/tutorial_3/Main3.kt), we have 2 coroutine that is set to run for 5 second and will be cancel in 3 second.

![tutorial_03_40](https://github.com/VoidHash/kotlin-coroutines/assets/8929413/5a6cdde7-320a-4df3-b4e9-a28534f9a636)
