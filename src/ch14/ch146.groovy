package ch14

/**
 * Synthesizing Methods for Specific Instances
 */
class Human2 {}
def emc = new ExpandoMetaClass(Human2)
emc.methodMissing = { String name, args ->
    "I'm Jack of all trades... I can $name"
}
emc.initialize()
def jack = new Human2()
def paul = new Human2()
jack.metaClass = emc
println jack.sing()
println jack.dance()
println jack.juggle()
try {
    paul.sing()
}catch (ex) {
    println ex
}
