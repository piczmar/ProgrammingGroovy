package ch7

/**
 * Maps
 */
langs = ['C++': 'Stroustrup', 'Java': 'Gosling', 'Lisp': 'McCarthy']
println langs.getClass().name

println langs['Java']
println langs['C++']
println langs.Java
println langs.'C++'

// when defining map you can also skip quotes around well behaved names
langs = ['C++' : 'Stroustrup' , Java : 'Gosling' , Lisp : 'McCarthy' ]