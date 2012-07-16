package ch18

/**
 * GroovyPizzaDSL
 */
def dslDef = new File('GroovyPizzaDSL.groovy').text
def dsl = new File('pizza.txt').text
def script = """
    ${dslDef}
    acceptOrder {
        ${dsl}
    }
"""
new GroovyShell().evaluate(script)
