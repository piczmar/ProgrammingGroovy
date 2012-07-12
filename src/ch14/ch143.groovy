package ch14

/**
 * Injecting methods using ExpandoMetaClass to instances
 * You can set the metaClass property for POGOs only. It’s read-only on
 * POJOs. Thus, instance-specific method injection is available only for
 * Groovy objects
 */
class Person {}

def emc = new ExpandoMetaClass(Human)
emc.sing = {->
    "oh baby baby..."
}
emc.initialize()

def jack = new Human()
def paul = new Human()

jack.metaClass = emc

println jack.sing()
try {
    paul.sing()
} catch (exc) {
    println exc
}

