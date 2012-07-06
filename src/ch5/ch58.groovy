package ch5

/**
 * Closure Delegation
 * Three properties of a closure determine which object handles a method call from within a closure.
 * These are
 *  - this,
 *  - owner,
 *  - delegate
 *  Order of method resolution is:
 *  1. this
 *  2. owner
 *  3. delegate
 */
def examiningClosure(closure) {
    closure()
}

examiningClosure() {
    println "In First Closure:"
    println "class is " + getClass().name
    println "this is " + this + ", super:" + this.getClass().superclass.name
    println "owner is " + owner + ", super:" + owner.getClass().superclass.name
    println "delegate is " + delegate +
            ", super:" + delegate.getClass().superclass.name
    // within the first closure, call a method and send it another closure defined within the first
    // closure, making the first closure the owner of the second closure
    examiningClosure() {
        println "In Closure within the First Closure:"
        println "class is " + getClass().name
        println "this is " + this + ", super:" + this.getClass().superclass.name
        println "owner is " + owner + ", super:" + owner.getClass().superclass.name
        println "delegate is " + delegate +
                ", super:" + delegate.getClass().superclass.name
    }
}

// example of method resolution
class Handler {
    def f1() { println "f1 of Handler called ..." }
    def f2() { println "f2 of Handler called ..." }
}
class Example {
    def f1() { println "f1 of Example called ..." }
    def f2() { println "f2 of Example called ..." }

    def foo(closure) {
        closure.delegate = new Handler()
        closure()
    }
}

def f1() { println "f1 of Script called..." }

new Example().foo {
    f1()
    f2()
}