package ch4

/**
 * Types
 * See also Groovy Math: http://groovy.codehaus.org/Groovy+Math
 */
def x = 1
int y = 1

println x.getClass().name       // res.: java.lang.Integer - all variables are objects in groovy, no primitives
println y.getClass().name
println 1.1.getClass().name     // res.: java.math.BigDecimal - groovy computations have higher precision by default
                                // and readily support java.math operations
println 1.byteValue()




