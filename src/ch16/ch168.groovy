package ch16

/**
 * Mocking using Expando
 */
class TestWithExpando extends GroovyTestCase {

    // this works pretty good thanks to Grovy dynamic typing, but you can make it better with expando
    void testMethodA() {
        // we will use Expando to test methodA, what we need is any object with write() method
        def testObj = new ClassWithDependency()
        def fileMock = new HandTossedFileMock()
        testObj.methodA(1, fileMock)
        assertEquals "The value is 1.", fileMock.result
    }

    void testMethodAWithExpando() {
        def fileMock = new Expando(text: '', write: { text = it })
        def testObj = new ClassWithDependency()
        testObj.methodA(1, fileMock)
        assertEquals "The value is 1.", fileMock.text
    }

    // you can also test using Map
    void testMethodAWithMap() {
        def text = ''
        def fileMock = [write: { text = it }]
        def testObj = new ClassWithDependency()
        testObj.methodA(1, fileMock)
        assertEquals "The value is 1.", text
    }
}
class HandTossedFileMock {
    def result

    def write(value) { result = value }
}
