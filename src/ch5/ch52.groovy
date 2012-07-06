package ch5

/**
 * Use of closures  - strategy pattern
 */
def totalSelectValues(n, closure) {
    total = 0
    for (i in 1..n) {
        if (closure(i)) {total += i}
    }
    total
}

print "Total of even numbers from 1 to 10 is "
println totalSelectValues(10) { it % 2 == 0 }
print "Total of odd numbers from 1 to 10 is "
println totalSelectValues(10) { it % 2 != 0}

class Equipment {
    def calculator

    Equipment(calc) { calculator = calc }

    def simulate() {
        println "Running simulation"
        calculator() // You may send parameters as well
    }
}
eq1 = new Equipment({println "Calculator 1"})
aCalculator = { println "Calculator 2" }
eq2 = new Equipment(aCalculator)
eq3 = new Equipment(aCalculator)
eq1.simulate()
eq2.simulate()
eq3.simulate()