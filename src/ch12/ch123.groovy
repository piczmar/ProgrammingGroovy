package ch12

/**
 * Dynamically accessing objects
 */
def printInfo(obj) {
// Assume user entered these values from standard input
    usrRequestedProperty = 'bytes'
    usrRequestedMethod = 'toUpperCase'
    println obj[usrRequestedProperty]
//or
    println obj."$usrRequestedProperty"
    println obj."$usrRequestedMethod"()
//or
    println obj.invokeMethod(usrRequestedMethod, null)
}

printInfo('hello')

// iterate over all properties of an object
println "Properties of 'hello' are: "
'hello'.properties.each { println it }