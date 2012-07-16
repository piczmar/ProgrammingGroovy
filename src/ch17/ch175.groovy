package ch17

/**
 * Creating custom builders - using FactoryBuilderSupport
 * You’ll use FactoryBuilderSupport if you’re working with well-defined node
 * names such as button, checkbox, label, and so on, in the SwingBuilder
 * Based on the node name, it delegates the node creation to different factories.
 */

// e.g. usage
def bldr = new RobotBuilder()
def robot = bldr.robot('iRobot') {
    forward(dist: 20)
    left(rotation: 90)
    forward(speed: 10, duration: 5)
}
robot.go()


class RobotBuilder extends FactoryBuilderSupport {
    {
        registerFactory('robot', new RobotFactory())
        registerFactory('forward', new ForwardMoveFactory())
        registerFactory('left', new LeftTurnFactory())
    };
}

class Robot {
    String name
    def movements = []

    void go() {
        println "Robot $name operating..."
        movements.each { movement -> println movement }
    }
}
class ForwardMove {
    def dist

    String toString() { "move distance... $dist" }
}
class LeftTurn {
    def rotation

    String toString() { "turn left... $rotation degrees" }
}

class RobotFactory extends AbstractFactory {
    def newInstance(FactoryBuilderSupport builder, name, value, Map attributes) {
        new Robot(name: value)
    }

    void setChild(FactoryBuilderSupport builder, Object parent, Object child) {
        parent.movements << child
    }
}
class ForwardMoveFactory extends AbstractFactory {
    boolean isLeaf() { true }

    def newInstance(FactoryBuilderSupport builder, name, value, Map attributes) {
        new ForwardMove()
    }

    boolean onHandleNodeAttributes(FactoryBuilderSupport builder,
                                   Object node, Map attributes) {
        if (attributes.speed && attributes.duration) {
            node.dist = attributes.speed * attributes.duration
            attributes.remove('speed')
            attributes.remove('duration')
        }
        true
    }
}
class LeftTurnFactory extends AbstractFactory {
    boolean isLeaf() { true }

    def newInstance(FactoryBuilderSupport builder, name, value, Map attributes) {
        new LeftTurn()
    }
}
