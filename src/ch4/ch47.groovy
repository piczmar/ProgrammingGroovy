package ch4

/**
 * Multimethods (multiple dispatch) - compare GiveRaiseJava.java for difference in behavior
 * Groovy smartly picks the correct implementation not only based on the target object—the object on which
 * the method is invoked — but also based on the parameter(s) you send to the call.
 */
void giveRaise(Employee employee) {
    employee.raise(new BigDecimal(10000.00))
}

giveRaise(new Employee())
giveRaise(new Executive())

/**
 * Compare how Java behaves in UsingCollection.java
 */
void testRemove(){
    ArrayList<String> lst = new ArrayList<String>();
    Collection<String> col = lst;
    lst.add("one" );
    lst.add("two" );
    lst.add("three" );
    lst.remove(0);
    col.remove(0);
    System.out.println("Added three items, remove two, so 1 item to remain." );
    System.out.println("Number of elements is: " + lst.size());
    System.out.println("Number of elements is: " + col.size());
}
testRemove()

/*
 //From: 'Programming Groovy Dynamic Productivity for the Java Developer' by Venkat Subramaniam
 Dynamic: To Be or Not to Be?
 Since Groovy is a dynamic language that supports optional typing, a
 good question to ask is, should you specify the type or rely on dynamic
 typing?

 There are no real rules in this area, but you can certainly develop some
 preferences.

 When programming in Groovy, I generally lean toward leaving out the
 type and instead making the parameter/variable names very expressive.
 Not specifying the type has the added advantages of benefiting
 from duck typing (Section 4.4, Design by Capability, on page 80) and
 from the ease of applying mocks for testing (Section 16.2, Unit Testing
 Java and Groovy Code, on page 236).

 I opt to specify the type if I am forced to (such as when JUnit requires
 test methods to be void) or if that provides a significant benefit (such as
 when mapping types to databases in GORM).

 If you’re developing an API that’s intended for use by someone using
 a static language, then I suggest you specify the parameter types for
 methods in the statically typed client-facing API.
 */
