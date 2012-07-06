package ch7

/**
 * Iterating over ArrayList
 */

lst = [3, 4, 7, 8, 9]
lst.each {println it}

// summing elements in collection
total = 0
lst.each { total += it }
println "Total is $total"

// double each element in collection
doubled = []
lst.each {doubled << 2 * it}
println "Doubled is $doubled"
// or using collect
println lst.collect { it * 2 }
