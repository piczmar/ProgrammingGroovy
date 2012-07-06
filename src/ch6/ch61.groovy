package ch6

/**
 * Strings
 */
// Groovy treats a String created using single quotes as a pure literal.
// So, if you put any expressions in it, Groovy won’t expand them
var = 5
println 'Value is ${var}'
println "Value is ${var}"

// groovy string are immutable like in Java
str = 'hello'
println str[2]
try {
    str[2] = '!'
}
catch (Exception ex) {
    println ex
}

// escape chars
value = 12
println "He paid \$${value} for that."

// lazy evaluation - when you print the string expression in text, the current value in the
// object referred to by what is used
what = new StringBuffer('fence')
text = "The cow jumped over the $what"
println text
what.replace(0, 5, "moon")
println text

// string types depending on creation style
// Groovy does not readily create an instance of GString simply because
// you use double quotes or slashes. It intelligently analyzes the string to
// determine whether it can get away with a simple regular String
def printClassInfo(obj) {
    println "class: ${obj.getClass().name}"
    println "superclass: ${obj.getClass().superclass.name}"
}

val = 125
printClassInfo("The Stock closed at ${val}")
printClassInfo(/The Stock closed at ${val}/)
printClassInfo("This is a simple String")
