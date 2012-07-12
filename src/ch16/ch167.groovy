package ch16

/**
 * Mocking using ExpandoMetaClass
 * This approach is useful only with Groovy code.
 * It does not help to mock methods called from within precompiled Java code.
 */
class TestUnitExpandoMetaClass extends GroovyTestCase{
    void testMyMethod() {
        def result

        CodeWithHeavierDependencies.metaClass.someAction = {-> 25}
        CodeWithHeavierDependencies.metaClass.println = {text -> result = text}

        def testObj = new CodeWithHeavierDependencies()

        testObj.myMethod()

        assertEquals(35,result)
    }
}
