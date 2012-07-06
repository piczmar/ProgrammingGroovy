package ch4

/**
 * Dynamic typing.
 * Relying on dynamic typing takes
 * more discipline, but it’s a small pain for a greater gain.
 */


/*
This is called duck typ-ing, which is based on the sentiment that "if it walks like a duck and
quacks like a duck, it must be a duck."
Classes that care to have that capability simply implement the method;
there’s no need to extend or implement anything. The result is low ceremony
and high productivity.*/
def takeHelp(helper){
    helper.helpMoveThings()
}

class Man{
    void helpMoveThings(){
        println "Man is helping.."
    }
}
class Woman{
    void helpMoveThings(){
        println "Woman is helping.."
    }
}
class Elephant{
    void helpMoveThings(){
        println "Elephant is helping.."
    }
    void eatSugarcane(){
        println "I love sugarcanes..."
    }
}
takeHelp(new Man())
takeHelp(new Woman())
takeHelp(new Elephant())

// two things that can help you a great deal here are
// disciplined unit testing and following good naming conventions.

def takeHelpAndReward(helper)
{
    helper.helpMoveThings()
    // this will help to verify that we invoke method on proper class
    if (helper.metaClass.respondsTo(helper, 'eatSugarcane' ))
    {
        helper.eatSugarcane()
    }
}
takeHelpAndReward(new Man())
takeHelpAndReward(new Woman())
takeHelpAndReward(new Elephant())
