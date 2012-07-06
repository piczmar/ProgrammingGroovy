package ch13

/**
 * AOP, interception
 */
//If a Groovy object implements the GroovyInterceptable interface,
//then its invokeMethod( ) is called for all its method calls.
//For other Groovy objects, it is called only for methods that are
//nonexistent at the time of call. The exception to this is if you
//implement invokeMethod( ) on its MetaClass. In that case, it is
//again called always for both types of methods.


class Car implements GroovyInterceptable {
    def check() { System.out.println "check called..." }

    def start() { System.out.println "start called..." }

    def drive() { System.out.println "drive called..." }

    def invokeMethod(String name, args) {
        System.out.print("Call to $name intercepted... ")
        if (name != 'check') {
            System.out.print("running filter... ")
            Car.metaClass.getMetaMethod('check').invoke(this, null)
        }

        def validMethod = Car.metaClass.getMetaMethod(name, args)
        if (validMethod != null) {
            validMethod.invoke(this, args)
        }
            //If the method is not found, simply route the request to the MetaClass,
            // This gives an opportunity for the method to be synthesized dynamically,
            // as you’ll see in Section 14.4, Method Synthesis Using methodMissing
            // If the method does not exist, MetaClass’s invokeMethod( ) will throw a MissingMethodException.
        else {
            return Car.metaClass.invokeMethod(this, name, args)
        }
    }
}

car = new Car()

car.start()
car.drive()
car.check()
try {
    car.speed()
}
catch (Exception ex) {
    println ex
}
