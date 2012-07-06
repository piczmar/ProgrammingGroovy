package ch5

/**
 * Dynamic closures
 */

// how to check that closure is passed
def doSth(closure) {
    if (closure) {
        return closure()
    }
    println "Using default implementation"
}

doSth()
doSth() {println "In closure"}

// how to determine the number of parameters
def completeOrder(amount, taxComputer) {
    tax = 0
    if (taxComputer.maximumNumberOfParameters == 2) {
        // expects task rate
        tax = taxComputer(amount, 6.05)
    } else {
        // uses a default rate
        tax = taxComputer(amount)
    }
    println "Sales tax is ${tax}"
}

completeOrder(100) {it * 0.0825}
completeOrder(100) {amount, rate -> amount * (rate / 100)}

// examining parameter types
def examine(closure) {
    println "$closure.maximumNumberOfParameters parameter(s) given:"
    for (aParameter in closure.parameterTypes) { println aParameter.name }
    println "--"
}

examine() { }
examine() { it }
examine() {-> }
examine() { val1 -> }
examine() {Date val1 -> }
examine() {Date val1, val2 -> }
examine() {Date val1, String val2 -> }

// Even when a closure is not using any parameters as in {} or { it }, it takes
// one parameter (whose name defaults to it). If the caller does not pass
// any values to the closure, then the first parameter (it) refers to null. If
// you want your closure to absolutely take no parameter, then you have
// to use the syntax {-> }—the lack of parameter before -> indicates that
// your closure takes 0 parameters
