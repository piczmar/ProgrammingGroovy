package ch16

/**
 * Mocking using Categories
 *
 * Categories are useful only with Groovy code. It does not help to mock
 * methods called from within compiled Java code.
 * The overriding approach is useful for both Java and Groovy code. However, the
 * overriding approach can’t be used if the class being tested is final. The
 * categories approach shines in this case.
 */

class TestUnitCategories extends GroovyTestCase {
    void testMyMethod() {
        def testObj = new CodeWithHeavierDependencies()
        use(MockHelper) {
            testObj.myMethod()
            assertEquals(35, MockHelper.result)
        }
    }
}
class MockHelper {
    def static result

    def static println(self, text) {result = text}

    def static someAction(CodeWithHeavierDependencies self) {25}
}
