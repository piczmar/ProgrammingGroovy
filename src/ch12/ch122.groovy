package ch12

/**
 * Check methods and properties
 */
str = "hello"
methodName = 'toUpperCase'
// Name may come from an input instead of being hard coded
methodOfInterest = str.metaClass.getMetaMethod(methodName)
println methodOfInterest.invoke(str)


print "Does String respond to toUpperCase()? "
println String.metaClass.respondsTo(str, 'toUpperCase') ? 'yes' : 'no'
print "Does String respond to compareTo(String)? "
println String.metaClass.respondsTo(str, 'compareTo', "test") ? 'yes' : 'no'
print "Does String respond to toUpperCase(int)? "
println String.metaClass.respondsTo(str, 'toUpperCase', 5) ? 'yes' : 'no'
