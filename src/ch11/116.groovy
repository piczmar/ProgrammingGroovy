package ch11

/**
 * Call script from another script
 */

shell = new GroovyShell()
shell.evaluate(new File("116a.groovy"))

name = "Marcin"
shell = new GroovyShell(binding)
result = shell.evaluate(new File('116b.groovy'))
println "116b returned : $result"
println "Hello $name"

binding1 = new Binding()
binding1.setProperty('name', 'Venkat')
shell = new GroovyShell(binding1)
shell.evaluate(new File('116b.groovy'))
binding2 = new Binding()
binding2.setProperty('name', 'Dan')
shell.binding = binding2
shell.evaluate(new File('116a.groovy'))
