package ch16

/**
 * Mocking by Overriding
 */

class TestCodeWithHeavierDependenciesUsingOverriding extends GroovyTestCase{
    void testMyMethod(){
        def testObj = new CodeWithHeavierDependenciesExt()
        testObj.myMethod()
        assertEquals(35, testObj.result)
    }
    void testMyMethodJava(){
        def testObj = new ExtendedJavaCode()

        def originalPrintStream = System.out
        def printMock = new PrintMock()
        System.out = printMock

        try{
            testObj.myMethod()
        }finally {
            System.out = originalPrintStream
        }
        assertEquals( 35, printMock.result)
    }
}
class CodeWithHeavierDependenciesExt extends CodeWithHeavierDependencies{
    def result
    int someAction() {return 25}
    def println(text){result = text}
}
class ExtendedJavaCode extends CodeWithHeavierDependenciesJava{
    int someAction(){return 25}
}
class PrintMock extends PrintStream{
    PrintMock(){super(System.out)}
    def result
    void println(int text){result = text}
}