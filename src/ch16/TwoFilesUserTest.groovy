package ch16

import groovy.mock.interceptor.MockFor

/**
 * Test with MockFor
 */
class TwoFilesUserTest extends GroovyTestCase {
    void testUseFiles() {
        def testObj = new TwoFileUser()
        def testData = "Multiple Files"

        def mock = new MockFor(FileWriter)
        mock.demand.write { it == testData}
        mock.demand.write {it == testData.size()}
        mock.demand.close(2..2){} // demand method close() to be called exactly 2 times

        mock.use{
            testObj.useFiles(testData)
        }
    }

}
