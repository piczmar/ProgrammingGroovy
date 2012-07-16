package ch16

import groovy.mock.interceptor.StubFor
import groovy.mock.interceptor.MockFor

/**
 * Mocking using Groovy Mock Library
 */
class TestUsingStubFor extends GroovyTestCase {

    void testMethodB() {
        def testObj = new ClassWithDependency()

        def fileStub = new StubFor(FileWriter.class)
        def text
        fileStub.demand.write {text = it.toString()}
        fileStub.demand.close {}

        // It failed to test whether the method was well behaved by closing the file.
        fileStub.use {
            testObj.methodB(1)
        }
        assertEquals "The value is 1.", text
    }

//    Stubs and mocks, however, do not help intercept calls to constructors.
//    So, in the previous example, the constructor of FileWriter is called, and
//    it ends up creating a file named output.txt on the disk.

    void testMethodBWithMockFor() {
        def testObj = new ClassWithDependency()

        def fileStub = new MockFor(FileWriter.class)
        def text
        fileStub.demand.write {text = it.toString()}
        fileStub.demand.close {}

        // It tests whether the method was well behaved by closing the file.
//        Unlike the stub, the mock tells you that even though your code produced
//        the desired result, it did not behave as expected. That is, it did
//        not call the close( ) method that was set up in the expectation using
//        demand.
        fileStub.use {
            testObj.methodB(1)
        }
        assertEquals "The value is 1.", text
    }

    void testMethodCWithMockFor() {
        def testObj = new ClassWithDependency()

        def fileStub = new MockFor(FileWriter.class)
        def text
        fileStub.demand.write {text = it.toString()}
        fileStub.demand.close {}

        // It tests whether the method was well behaved by closing the file.
        fileStub.use {
            testObj.methodC(1)
        }
        assertEquals "The value is 1.", text
    }

}
