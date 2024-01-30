## Supervisor Job

When the things go wrong with a coroutine, we need to handler these exceptions in order
to not get an error stack trace. To do that we use a *CoroutineExceptionHandler*. This
is a helper function for coroutine builder implementations to handle uncaught and 
unexpected exceptions in coroutines, that could not be otherwise handled in a 
normal way through structured concurrency.

```kotlin
val coroutineExceptionHandler = CoroutineExceptionHandler { _, e ->
    println("My caught exception: $e")
}
val job = Job()
val scope = CoroutineScope(job + coroutineExceptionHandler + Dispatchers.Default)
```
But we still have a problem, whenever a coroutine is cancelled, all coroutine associate
to that *Job* will be canceled too. To avoid that we need to use an *SupervisorJob*. This
is a special type of Job that changes the way failure is handled. A typical job would 
cancel all its children if it fails. However, a *SupervisorJob* allows its children to 
fail independently of each other, without causing a cascade of cancellations. So a 
supervisor can implement a custom policy for handling failures of its children:
- A failure of a child job that was created using launch can be handled via *CoroutineExceptionHandler* in the context.
- A failure of a child job that was created using async can be handled via *Deferred.await* on the resulting 
deferred value.

With this [example](), it is easy to understand how both *Job* and *SupervisorJob* works. To help us,
we use a *CoroutineExceptionHandler* to handler any exception that we caught.



