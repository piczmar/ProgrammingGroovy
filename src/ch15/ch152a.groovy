package ch15

/**
 * Method delegation - putting it all together
 * Refactored ch152
 */

ExpandoMetaClass.enableGlobally()

Object.metaClass.delegateCallsTo = { Class... classOfDelegates ->
    def objectOfDelegates = classOfDelegates.collect {it.newInstance()}
    delegate.metaClass.methodMissing = {String name, args ->
        println "intercepting call to $name"

        def delegateTo =objectOfDelegates.find {it.metaClass.respondsTo(it,name,args)}
        if(delegateTo){
            delegate.metaClass."$name" = {Object[] varArgs ->
                def params = varArgs?:null
                return delegateTo.invokeMethod(name, params)
            }
            return delegateTo.invokeMethod(name, args)
        }else{
            throw new MissingMethodException(name, delegate.getClass(), args)
        }
    }
}

class Manager2{
    {delegateCallsTo Worker, Expert, GregorianCalendar}
    def schedule(){println "Scheduling..."}
}
peter = new Manager2()
peter.schedule()
peter.simpleWork1('fast')
peter.simpleWork1('quality')
peter.simpleWork2()
//peter.simpleWork2()
peter.advancedWork1('fast')
peter.advancedWork1('quality')
peter.advancedWork2('protype', 'fast')
peter.advancedWork2('product', 'quality')
println "Is 2008 a leap year? " + peter.isLeapYear(2008)
try {
    peter.simpleWork3()
}
catch (Exception ex) {
    println ex
}
