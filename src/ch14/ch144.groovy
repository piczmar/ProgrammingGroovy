package ch14

/**
 * Method synthesis using methodMissing
 * When users call a nonexistent method, you can intercept it and create an implementation on the fly.
 */
class Player {
    def work() {"working..."}

    def plays = ['Tennis', 'VolleyBall', 'BasketBall']

    def methodMissing(String name, args) {
        println "methodMissing called for $name"
        def methodInList = plays.find {it == name.split('play')[1]}
        if (methodInList) {
            return "playing ${name.split('play')[1]} ..."
        } else {
            throw new MissingMethodException(name, Human.class, args)
        }
    }
}
jack = new Player()
println jack.work()
println jack.playTennis()
println jack.playBasketBall()
println jack.playTennis()
try {
    jack.playPolitics()
} catch (exc) {
    println "Error: " + exc
}

//Repeated calls to a nonexistent method, such as playTennis( ), involve
//the same performance hit to evaluate. You can make this efficient by
//injecting the method on first invocation

// does not work this:
class Player2 {
    def work() { "working..." }

    def plays = ['Tennis', 'VolleyBall', 'BasketBall']

    def methodMissing(String name, args) {
        System.out.println "methodMissing called for $name"
        def methodInList = plays.find { it == name.split('play')[1]}
        if (methodInList) {
            def impl = { Object[] vargs ->
                return "playing ${name.split('play')[1]}..."
            }
            Player2.metaClass."$name" = impl //future calls will use this
            return impl(args)
        }
        else {
            throw new MissingMethodException(name, Player2.class, args)
        }
    }

    static { Player2.metaClass }
}
jack = new Player2()
println jack.work()
println jack.playTennis()
println jack.playTennis()

 // does not work this:
ExpandoMetaClass.enableGlobally()
class Person2 implements GroovyInterceptable {
    def work() {"working..."}

    def plays = ['Tennis', 'VolleyBall', 'BasketBall']

    def invokeMethod(String name, args) {
        println "* intercepting call for $name"
        def method = metaClass.getMetaMethod(name, args)
        if (method) {
            return method.invoke(this, args)
        } else {
            return metaClass.invokeMissingMethod(this, name, args)
        }
    }

    def methodMissing(String name, args) {
        println "* methodMissing caled for $name"
        def methodInList = plays.find {it == name.split('play')[1]}
        if (methodInList) {
            def impl = { Object[] vargs ->
                return "playing ${name.split('play')[1]}..."
            }
            Person2.metaClass."$name" = impl //future calls will use this
            return impl(args)
        } else {
            throw new MissingMethodException(name, Person2.class, args)
        }
    }
}
jack = new Person2()
println jack.work()
println jack.playTennis()
println jack.playTennis()



