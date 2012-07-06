package ch7

/**
 * Collections convenience methods
 */

// find total number of characters
lst = ['Programming', 'In', 'Groovy']
count = 0
lst.each { count += it.size() }
println count
println lst.collect { it.size() }.sum()
// inject( ) calls the closure for each element of the collection. The element
// is represented, in this example, by the element parameter. inject( ) takes
// as a parameter an initial value that it will inject, through the carryOver
// parameter, into the first call to the closure. It then injects the result
// from the closure into the subsequent call to the closure
println lst.inject(0) { carryOver, element -> carryOver + element.size() }

// concatenate elements of collection
println lst.join(' ')

// flattening collection
lst[0] = ['Be', 'Productive']
println lst
lst = lst.flatten()
println lst

// removing elements from list
println lst - ['Productive', 'In']

// create reversed list
lst = lst.reverse()
println lst

println lst.size() // method on list
println lst*.size() // method on each element of the list, same as: lst.collect { it.size() }

// you can pass a list as arguments to method taking multiple params
def words(a, b, c, d) {
    println "$a $b $c $d"
}

words(*lst) // * operator splits collection into individual objects

