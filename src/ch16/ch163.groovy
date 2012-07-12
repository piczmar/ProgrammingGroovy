package ch16

/**
 * Testing for Exceptions
 */

class MathTest extends GroovyTestCase {
    def divide(x,y){
        x/y
    }
    void testDivideZero() {
        shouldFail {divide(2, 0)}
        shouldFail(ArithmeticException) { divide(2, 0) }
        shouldFail ArithmeticException, { divide(2, 0) }
    }
}
