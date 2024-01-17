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

In this [exemple](), we set the *timerJob* as a *NonCancellable* coroutine, when we try to
cancel both of then, only *taskJob* coroutine will be canceled while *timerJob* will finish
to be executed.

[tutorial_4]
