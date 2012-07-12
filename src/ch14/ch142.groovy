package ch14

/**
 * Injecting methods using ExpandoMetaClass to classes
 */

Integer.metaClass.daysFromNow = {
    Calendar today = Calendar.instance
    today.add(Calendar.DAY_OF_MONTH, delegate)
    today.time
}

println 5.daysFromNow()

// to define a new property do like that:

Integer.metaClass.getDaysFromNow = {
    Calendar today = Calendar.instance
    today.add(Calendar.DAY_OF_MONTH, delegate)
    today.time
}

println 5.daysFromNow

// You injected a method on Integer, but what about its cousins Short and Long?
// The previous method is not available on these classes. You can do like that:

daysFromNow = {->
    Calendar today = Calendar.instance
    today.add(Calendar.DAY_OF_MONTH, (int) delegate)
    today.time
}
Integer.metaClass.daysFromNow = daysFromNow
Long.metaClass.daysFromNow = daysFromNow

println 5.daysFromNow()
println 5L.daysFromNow()

// alternativeley you can provide a method in the base class Number
Number.metaClass.someMethod = {->
    println "someMethod called"
}

2.someMethod()
2L.someMethod()

//You might also want to introduce methods into an interface hierarchy so the methods
//are available on all classes implementing that interface.
//You can inject static methods into a class as well. You add static methods
//to the static property of the MetaClass

Integer.metaClass.static.isEven = { val ->
    val % 2 == 0
}
println "Is 2 even? " + Integer.isEven(2)
println "Is 3 even? " + Integer.isEven(3)

// you can also add new constructors, operator << will result in an error if you use it tof existing constructors or methods
Integer.metaClass.constructor << {Calendar calendar ->
    new Integer(calendar.get(Calendar.DAY_OF_YEAR))
}
println new Integer(Calendar.instance)

// you can override constructor with = operator
Integer.metaClass.constructor = { int val ->
    println "Intercepting constructor call"
    constructor = Integer.class.getConstructor(Integer.TYPE)
    constructor.newInstance(val)
}
println new Integer(4)
println new Integer(Calendar.instance)
