package ch5

/*  Coroutines:

Calling a function or method creates a new scope in the execution
sequence of a program. You enter the function at one entry point (top).
Once you complete the method, you return to the caller’s scope.

Coroutines, on the other hand, allow a function to have multiple entry
points, each following the place of the last suspended call. You can
enter a function, execute part of it, suspend, and go back to execute
some code in the context or scope of the caller. You can then resume
execution of the function from where you suspended. Coroutines are
handy to implement some special logic or algorithms, such as in a
producer-consumer problem. A producer receives some input, does initial
processing on it, and notifies a consumer to take that processed
value for further computation and output or storage. The consumer
does its part and, when done, notifies the producer to get more input.
In Java, wait( ) and notify( ) help you implement coroutines when combined
with multithreading. Closures give the impression (or illusion) of
coroutines in a single thread.
 */

def iterate(n, closure) {
    1.upto(n) {
        println "in iterate with value ${it}"
        closure(it)
    }
}

println "calling iterate"
total = 0
iterate(5, {
    total += it
    println "in closure, total so far is ${total}"
})
println "done"
