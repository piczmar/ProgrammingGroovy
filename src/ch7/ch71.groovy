package ch7

/**
 * Lists
 */
lst = [1, 3, 4, 1, 8, 9, 2, 6]
println lst
println lst.getClass().name
println lst[0]
println lst[lst.size() - 1]
// You can use negative index values, and Groovy will traverse from right instead of left
println lst[-1]

subList = lst[2..5] // be aware, you did not get a copy—if you change an
                    // element using one list, you’re affecting the other
println subList
println subList.getClass().name
println subList.dump()
subList[0] = 55
println "After subList[0]=55 lst = $lst"
println lst[-6..-3]