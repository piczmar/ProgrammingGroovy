package ch13

/**
 * Intercepting Methods Using MetaClass
 */

// in this version the car does not implement GroovyInterceptable
class Car2 {
    def check() { System.out.println "check called..." }

    def start() { System.out.println "start called..." }

    def drive() { System.out.println "drive called..." }
}

// This method will now intercept all calls on an instance of Car2.
Car2.metaClass.invokeMethod = { String name, args ->
    System.out.print("Call to $name intercepted... ")
    if (name != 'check') {
        System.out.print("running filter... ")
        Car2.metaClass.getMetaMethod('check').invoke(delegate, null)  //The delegate within the intercepting
        // closure refers to the target object whose methods
        // are being intercepted.
    }

    def validMethod = Car2.metaClass.getMetaMethod(name, args)
    if (validMethod != null) {
        validMethod.invoke(delegate, args)
    }
    //If the method is not found, simply route the request to the MetaClass,
    // This gives an opportunity for the method to be synthesized dynamically,
    // as you’ll see in Section 14.4, Method Synthesis Using methodMissing
    // If the method does not exist, MetaClass’s invokeMethod( ) will throw a MissingMethodException.
    else {
        return Car.metaClass.invokeMissingMethod(delegate, name, args)
    }
}


car = new Car2()

car.start()
car.drive()
car.check()
try {
    car.speed()
}
catch (Exception ex) {
    println ex
}

// you can intercept calls on POJOs as well
Integer.metaClass.invokeMethod = { String name, args ->
    System.out.println("Call to $name intercepted on $delegate... ")
    def validMethod = Integer.metaClass.getMetaMethod(name, args)
    if (validMethod == null) {
        return Integer.metaClass.invokeMissingMethod(delegate, name, args)
    }
    System.out.println("running pre-filter... ")
    result = validMethod.invoke(delegate, args) // Remove this for around-advice
    System.out.println("running post-filter... ")
    result
}
println 5.floatValue()
println 5.intValue()
try {
    println 5.empty()
}
catch (Exception ex) {
    println ex
}

// meta class characteristics
class MyClass {}
println "MetaClass of 2 is " + 2.metaClass.getClass().name
println "MetaClass of Integer is " + Integer.metaClass.getClass().name
println "MetaClass of 2 now is " + 2.metaClass.getClass().name
obj1 = new MyClass()
println "MetaClass of obj1 is " + obj1.metaClass.getClass().name
println "MetaClass of MyClass is " + MyClass.metaClass.getClass().name
println "MetaClass of obj1 still is " + obj1.metaClass.getClass().name
obj2 = new MyClass()
println "MetaClass of obj2 created later is " + obj2.metaClass.getClass().name