package ch8

/**
 * Groovy enhancements to JDK
 */
// print info about an object
str = "Hello"
println str.dump()
println str.inspect()  //This method is intended to tell you what input would be needed to create an object

class Person {
    String name
    String surname

    String toString() {
        return "$name, $surname"
    }
}

person = new Person()
println person.inspect()

// you can set a context using the identity( ) method - all calls within closure passed to this method
// are invoked in context of object on which identity() was invoked
lst = [1, 2]
lst.identity {
    add(3)
    add(4)
    println size()
    println contains(2)
}
lst.identity {
    println "this is ${this},"
    println "owner is ${owner},"
    println "delegate is ${delegate}."
}

// sleep
thread = Thread.start {
    println "Thread started"
    startTime = System.nanoTime()
    new Object().sleep(2000)
    endTime = System.nanoTime()
    println "Thread done in ${(endTime - startTime) / 10 ** 9} seconds"
}