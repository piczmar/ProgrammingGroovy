package ch6

/**
 * Regex
 */
obj = ~"hello"
println obj.getClass().name

//You can use either (single or double) quotes or slashes to
//create a RegEx. The slashes have an added advantage that you don’t
//have to escape backslashes. So, /\d*\w*/ is an equivalent and elegant
//cousin of "\\d*\\w*".

pattern = ~"(G|g)roovy"
text = 'Groovy is Hip'
if (text =~ pattern) // partial match
    println "match"
else
    println "no match"
if (text ==~ pattern)  // exact match
    println "match"
else
    println "no match"

matcher = 'Groovy is groovy' =~ /(G|g)roovy/
print "Size of matcher is ${matcher.size()} "
println "with elements ${matcher[0]} and ${matcher[1]}."

str = 'Groovy is groovy, really groovy'
println str
result = (str =~ /groovy/).replaceAll('hip' )
println result
