package ch3

/**
 * Support for Java 5 features
 * See also differences form Java http://groovy.codehaus.org/Differences+from+Java
 */

// autoboxing - Groovy treats primitives as objects always so it does not cast them constantly like Java
int x = 6
println x.getClass().name

// for-each
greetings = ["Hello", "Hi", "Howdy"]
for(String greet: greetings){
    println greet
}
for(greet in greetings){
    println greet
}

// enums
enum CoffeSize{ SHORT, SMALL, MEDIUM, LARGE, MUG}
def orderCoffee(size){
    switch(size){
        case[CoffeSize.SHORT, CoffeSize.SMALL]:
            println "you're health conscous"
            break
        case CoffeSize.MEDIUM..CoffeSize.LARGE:
            println "you gotta be a programmer"
            break
        case CoffeSize.MUG:
            println "you should try Caffeine IV"
            break
    }
}
orderCoffee(CoffeSize.MEDIUM)
orderCoffee(CoffeSize.MUG)
orderCoffee(CoffeSize.SHORT)

// java allows to override methods for specific enum e.g.:
enum WeekendActivity{
    SATURDAY{
        String activity(){'Play'}
    },
    SUNDAY;
    String activity(){'Relax'}
}
for(day in WeekendActivity.values()){
    println "$day - ${day.activity()}"
}
// in Groovy similar could be done like:
def emc = new ExpandoMetaClass(WeekendActivity)
emc.activity = {-> 'Play'}
emc.initialize()
WeekendActivity.SATURDAY.metaClass = emc
for(day in WeekendActivity.values()){
    println "$day - ${day.activity()}"
}

// varargs
def foo1(int a, int... b){
    println "you passed $a and $b"
}
def foo2(int a, int[] b){
    println "you passed $a and $b"
}
foo1(1, 2, 3, 4, 5)
foo2(1, 2, 3, 4, 5)

// static imports - in Groovy you can define aliases
import static Math.random as rand
import groovy.lang.ExpandoMetaClass as EMC
double value = rand()
def metaClass = new EMC(Integer)
assert metaClass.getClass().name=='groovy.lang.ExpandoMetaClass'

// gotchas

// - return optional
def isPalindrome1(str){str==str.reverse()}
def isPalindrome2(str){
    if(str){
        str == str.reverse()
    }else{
        false
    }
}
println "mom is palindrome? ${isPalindrome1('mom')}"
println "mom is palindrome? ${isPalindrome2('mom')}"
println "mom is palindrome? ${isPalindrome1('mo')}"
println "mom is palindrome? ${isPalindrome2('mo')}"

// - Groovy's == is equal to Java's equals or compareTo if class implements Comparable
str1 = 'hello'
str2 = str1
str3 = new String('hello')
str4 = 'Hello'
println "str1 == str2: ${str1==str2}"
println "str1 == str3: ${str1==str3}"
println "str1 == str4: ${str1==str4}"

println "str1.is(str2): ${str1.is(str2)}"
println "str1.is(str3): ${str1.is(str3)}"
println "str1.is(str4): ${str1.is(str4)}"

class A{
    boolean equals(other){
        println "equals called"
        false
    }
}
class B implements Comparable{
    boolean equals(other){
        println "equals called"
        false
    }
    int compareTo(other){
        println "compareTo called"
        0
    }
}
new A() == new A()
new B() == new B()

// no compile-time type checking
// runtime is done: x = (TypeOfX)y
//Integer v = 4
//v ="hello"   //GroovyCastException, check bytecode: javap -c ClassFileName

//Integer val = 4
//val.blah()   //MissingMethodException

// different syntax for creating primitive arrays
//int [] arr = new int []{1, 2, 3};                // this won't work
int [] arr = [1, 2, 3]
println arr.getClass().name


