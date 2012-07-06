package ch11;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Calling groovy script from Java thanks to JSR223
 * You’re sending a String object (with value Venkat) to the engine using
 * the put( ) method. You’ve given the name name for the variable binding.
 * Within the script, you use that variable (name). You can also set values
 * to it. This value can be obtained on the Java side by calling the get( )
 * method on the engine.
 * JSR 223 provides the capability to call instance methods and also
 * functions not associated with any particular class. You can use the
 * invokeMethod( ) and invokeFunction( ) of the Invocable for that. If you plan
 * to use a script repeatedly, use the Compilable interface to avoid repeatedly
 * recompiling the script.
 */
public class RunScript {
    public static void main(String[] args) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("groovy");
        System.out.println("Calling script from Java");
        try {
            engine.eval("println 'Hello from Groovy'");

            engine.eval(new FileReader("E:\\dev\\groovy\\ProgrammingGroovy\\src\\ch11\\116a.groovy"));

            engine.put("name", "Venkat");
            engine.eval("println \"Hello ${name} from Groovy\"; name += '!' ");
            String name = (String) engine.get("name");
            System.out.println("Back in Java:" + name);
        } catch (ScriptException ex) {
            System.out.println(ex);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
