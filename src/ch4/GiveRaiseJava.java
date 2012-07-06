package ch4;

import java.math.BigDecimal;

/**
 * Example for ch47.groovy
 */
public class GiveRaiseJava {
    public static void giveRaise(Employee employee) {
        employee.raise(new BigDecimal(10000.00));
    }

    public static void main(String[] args) {
        giveRaise(new Employee());
        giveRaise(new Executive());
    }
}