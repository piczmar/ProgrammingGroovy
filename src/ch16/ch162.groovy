package ch16

/**
 * Unit testing
 */

class ListTest extends GroovyTestCase {
    void testListSize() {
        def lst = [1, 2]
        assertEquals("ArrayList size must be 2", 2, lst.size())
    }
}
class CarTest extends GroovyTestCase {
    def car

    void setUp() {
        car = new Car()
    }

    void testInitialize() {
        assertEquals 0, car.miles
    }

    void testDrive() {
        car.drive(10)
        assertEquals 10, car.miles
    }

    void testDriveNegativeInput(){
        car.drive(-10)
        assertEquals(0,car.miles)
    }
}