## Sequential code

In a sequential code, each line needs to complete execute before the next one start
to be executed as well.

In this [example](https://github.com/VoidHash/kotlin-coroutines/blob/master/src/main/kotlin/tutorial_1/Main1.kt), we set 5 seconds to *backgroundTask* be completed, printing only it's begin and end. After that the 
function *printTaskTime* start, printing their progress every second, until finish.

![tutorial_01_40](https://github.com/VoidHash/kotlin-coroutines/assets/8929413/76586d76-da68-4b54-bf75-fdbff440f2a2)
<p></p>
Show a sequential code execution
