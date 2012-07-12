package ch16;

public class CodeWithHeavierDependenciesJava {
    public void myMethod() throws InterruptedException {
        int value = someAction() + 10;
        System.out.println(value);
    }

    int someAction() throws InterruptedException {
        Thread.sleep(5000); // simulates time consuming action
        return (int) Math.random() * 100;// Simulated result of some action
    }
}