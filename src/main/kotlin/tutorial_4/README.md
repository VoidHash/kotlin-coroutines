## Non cancellable coroutine

We can define a coroutine to not be canceled. A coroutine defined this way can't be
canceled even if their context is canceled. 

To set a coroutine to be a non-cancellable we just need use *NonCancellable* parameter
in our *.launch( )*

```kotlin
val coroutineScope = CoroutineScope(Dispatchers.Default)
val nonCancellableCoroutine = coroutineScope.launch(NonCancellable) {
            // Your logic here
    }
```

In this [exemple](https://github.com/VoidHash/kotlin-coroutines/blob/master/src/main/kotlin/tutorial_4/Main4.kt), we set the *timerJob* coroutine as *NonCancellable*, when we try to
cancel both of then, only *taskJob* coroutine will be canceled while *timerJob* will finish
to be executed.

![tutorial_04_40](https://github.com/VoidHash/kotlin-coroutines/assets/8929413/a9ab66ea-465c-4238-87fc-b497f42285c3)
