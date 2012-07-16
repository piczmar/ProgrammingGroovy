package ch16

/**
 * Mocking with Map
 */

/**
 * Mocking using Expando
 */
class TestWithMap extends GroovyTestCase {

    // you can also test using Map
    void testMethodA() {
        def text = ''
        def fileMock = [write: { text = it }]
        def testObj = new ClassWithDependency()
        testObj.methodA(1, fileMock)
        assertEquals "The value is 1.", text
    }
}

