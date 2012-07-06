package ch3

/**
 * example how booleans are evaluated
 */

// if you place object where boolean is expected groovy checks whether a reference is null,
// it considers null as false and true depends on object type:
// - Collection:   non-empty
// - Character:     value not 0
// - CharSequence:  length > 0
// - Enumeration:   hasMoreElements==true
// - Iterator:      hasNext==true
// - Number:        double value != 0
// - Map:           non-empty
// - Matcher:       at least one match
// - Object[]:      length > 0
// - any other type:reference != null

str = "hello"
if(str) { println 'hello'}

lst = null
println lst ? 'lst true' : 'lst false'
lst = [1, 2, 3]
println lst ? 'lst true' : 'lst false'
lst = []
println lst ? 'lst true' : 'lst false'
