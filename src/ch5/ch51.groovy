package ch5

/**
 * Closures http://groovy.codehaus.org/Closures+-+Formal+Definition
 */
def sum(n) {
    total = 0
    for (int i = 2; i <= n; i += 2) {
        total += i
    }
    total
}

def product(n) {
    prod = 1
    for (int i = 2; i <= n; i += 2) {
        prod *= i
    }
    prod
}

def sqr(n) {
    squared = []
    for (int i = 2; i <= n; i += 2) {
        squared << i ** 2
    }
    squared
}
println "Sum of even numbers from 0 to 10: ${sum(10)}"
println "Product of even numbers from 0 to 10: ${product(10)}"
println "Squares of even numbers from 0 to 10: ${sqr(10)}"

// now with closure you can remove duplicated code like:
def pickEven(n, block){
    for(int i=2; i<=n; i+=2){
        block(i)
    }
}

pickEven(10,{println it})
total = 0
pickEven(10) { total += it }
println "Sum of even numbers from 1 to 10 is ${total}"
product = 1
pickEven(10) { product *= it }
println "Product of even numbers from 1 to 10 is ${product}"