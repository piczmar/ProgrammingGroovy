package ch15

/**
 * Method delegation - putting it all together
 */

//In the following example, a Manager wants to delegate work to either a Worker or an Expert
ExpandoMetaClass.enableGlobally()

class Worker {
    def simpleWork1(spec) {println "worker does work1 with spec $spec"}

    def simpleWork2() {println "worker does work2"}
}
class Expert {
    def advancedWork1(spec) {"expert does work1 with spec $spec"}

    def advancedWork2(scope, spec) {println "expert does work2 with scope $scope spec $spec"}
}
class Manager {
    def worker = new Worker()
    def expert = new Expert()

    def schedule() {println "Scheduling..."}

    def methodMissing(String name, args) {
        println "intercepting call to $name"
        def delegateTo = null

        if (name.startsWith("simple")) {delegateTo = worker}
        if (name.startsWith("advanced")) {delegateTo = expert}

        if (delegateTo?.metaClass.respondsTo(delegateTo, name, args)) {
            Manager.metaClass."${name}" = {Object[] varArgs ->
                return delegateTo.invokeMethod(name, varArgs)
            }
            return delegateTo.invokeMethod(name, args)
        }
        throw new MissingMethodException(name, Manager.class, args)
    }
}

peter = new Manager()
peter.schedule()
peter.simpleWork1('fast')
peter.simpleWork1('quality')
peter.simpleWork2()
//peter.simpleWork2()
peter.advancedWork1('fast')
peter.advancedWork1('quality')
peter.advancedWork2('protype', 'fast')
peter.advancedWork2('product', 'quality')
try {
    peter.simpleWork3()
}
catch (Exception ex) {
    println ex
}
