package ch7

/**
 * Collection finder methods
 */
lst = [4, 3, 1, 2, 4, 1, 8, 9, 2, 6]
println lst.find { it == 2 } // gets you the first occurrence of the matching object
println lst.find { it > 4 }

println lst.findAll { it == 2 } // gets all occurrences, like collect()
println lst.findIndexOf {it == 2} // get first occurrence index
println lst.findIndexValues {it == 2} // get indexes of all occurrences