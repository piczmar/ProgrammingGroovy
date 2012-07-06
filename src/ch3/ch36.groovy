package ch3

/**
 * Operator overloading, used for DSL creation (ch18)
 * Impossible in Java but can be done in Groovy
 */
for(i = 'a'; i<'d'; i++){  // ++ operator maps to next() method on the String class
    println i
}

for(i in 'a'..'c'){
    println i
}

// << on collection translates to leftShift()
lst = ["hello"]
lst << "there"
println lst

// here's an example how to add own mappings to a class
class ComplexNumber{
    def real, imaginary
    def plus(other){
        new ComplexNumber(real: real + other.real, imaginary: imaginary + other.imaginary)
    }
    String toString(){"${real} ${imaginary > 0 ? '+' : ''} ${imaginary}i"}
}
c1 = new ComplexNumber(real: 1, imaginary: 2)
c2 = new ComplexNumber(real: 4, imaginary: 1)
println c1+c2