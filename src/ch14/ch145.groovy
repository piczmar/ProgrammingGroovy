package ch14

/**
 * Method synthesis using ExpandoMetaClass
 */
//invokeMethod( ) is a method of GroovyObject. methodMissing( )
//was introduced later in Groovy and is part of the MetaClassbased
//method handling. If your objective is to handle calls to
//nonexisting methods, implement methodMissing( ) because this
//involves low overhead. If your objective is to intercept calls to
//both existing and nonexisting methods, use invokeMethod( ).

ExpandoMetaClass.enableGlobally()
class Human {
    def work() { "working..." }
}
Human.metaClass.invokeMethod = { String name, args ->
    System.out.println "intercepting call for ${name}"
    def method = Human.metaClass.getMetaMethod(name, args)
    if (method) {
        return method.invoke(delegate, args)
    }
    else {
        return Human.metaClass.invokeMissingMethod(delegate, name, args)
    }
}
Human.metaClass.methodMissing = { String name, args ->
    def plays = ['Tennis', 'VolleyBall', 'BasketBall']
    System.out.println "methodMissing called for $name"
    def methodInList = plays.find { it == name.split('play')[1]}
    if (methodInList) {
        def impl = { Object[] vargs ->
            return "playing ${name.split('play')[1]}..."
        }
        Human.metaClass."$name" = impl //future calls will use this
        return impl(args)
    }
    else {
        throw new MissingMethodException(name, Human.class, args)
    }
}
jack = new Human()
println jack.work()
println jack.playTennis()
println jack.playTennis()
try {
    jack.playPolitics()
}
catch (ex) {
    println ex
}
