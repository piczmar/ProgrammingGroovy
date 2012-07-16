package ch17

/**
 * Creating custom builders - using BuilderSupport
 *
 * BuilderSupport provides four versions of createNode().
 * The appropriate method is called when you invoke methods on an instance of the builder.
 * The setParent( ) is called to let you (the author of the builder) know the parent
 * of the current node being processed.
 * Whatever you return from createNode() is considered to be a node, and the builder support sends that
 * as a parameter to nodeCompleted( ).
 * The BuilderSupport does not handle missing properties like it handles methods.
 * However, you can still use the propertyMissing( ) method to handle those cases.
 */

class TodoBuilderWithSupport extends BuilderSupport {

    int level = 0
    def result = new StringWriter()

    void setParent(Object parent, Object child) {}

    def createNode(name) {
        if (name == 'build') {
            result << "To-Do:\n"
            return 'buildnode'
        } else {
            return handle(name, [:])
        }

    }

    def createNode(Object name, Object value) {
        throw new Exception("Invalid format")
    }

    def createNode(name, Map attribute) {
        handle(name, attribute)
    }

    def createNode(Object name, Map attribute, Object value) {
        throw new Exception("Invalid format")
    }

    def propertyMissing(String name) {
        handle(name, [:])
        level--
    }

    void nodeCompleted(parent, node) {
        level--
        if (node == 'buildnode') {
            println result
        }
    }

    def handle(String name, attributes) {
        level++
        level.times { result << " " }
        result << placeXifStatusDone(attributes)
        result << name.replaceAll("_", " ")
        result << printParameters(attributes)
        result << "\n"
        name
    }

    def placeXifStatusDone(attributes) {
        attributes['status'] == 'done' ? "x " : "- "
    }

    def printParameters(attributes) {
        def values = ""
        if (attributes.size() > 0) {
            values += " ["
            def count = 0
            attributes.each { key, value ->
                if (key == 'status') return
                count++
                values += (count > 1 ? " " : "")
                values += "${key}: ${value}"
            }
            values += "]"
        }
        values
    }
}

// usage:
bldr = new TodoBuilderWithSupport()
bldr.build {
    Prepare_Vacation(start: '02/15', end: '02/22') {
        Reserve_Flight(on: '01/01', status: 'done')
        Reserve_Hotel(on: '01/02')
        Reserve_Car(on: '01/02')
    }
    Buy_New_Mac {
        Install_QuickSilver
        Install_TextMate
        Install_Groovy {
            Run_all_tests
        }
    }
}
