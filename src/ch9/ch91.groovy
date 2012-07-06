package ch9

/**
 * Parsing XML
 * GPath allows you to navigate the hierarchy of objects (POJOs and POGOs)
 * and XML—you can traverse the hierarchy using the . (dot) notation
 */

// using DOMCategory
inputStream = this.getClass().getClassLoader().getResourceAsStream("ch9/languages.xml")
document = groovy.xml.DOMBuilder.parse(new InputStreamReader(inputStream))

rootElement = document.documentElement
use(groovy.xml.dom.DOMCategory) {
    println 'Languages and authors:'
    languages = rootElement.language
    languages.each { language ->
        println "${language.'@name'} authored by ${language.author[0].text()}"
    }

    def languagesByAuthor = { authorName ->
        languages.findAll { it.author[0].text() == authorName }.collect {it.'@name'}.join(', ')
    }
    println "Languages by Wirth:" + languagesByAuthor('Wirth')
}

// using XMLParser
// There are a few downsides to using XMLParser, which may be a concern
// to you depending on your needs. It does not preserve the XML
// InfoSet. It ignores the XML comments and processing instructions in
// your document.
inputStream = this.getClass().getClassLoader().getResourceAsStream("ch9/languages.xml")
languages = new XmlParser().parse(new InputStreamReader(inputStream))
println "Languages and authors"
languages.each {
    println "${it.@name} authored by ${it.author[0].text()}"
}
def languagesByAuthor = { authorName ->
    languages.findAll { it.author[0].text() == authorName }.collect {it.@name}.join(', ')
}
println "Languages by Wirth:" + languagesByAuthor('Wirth')

// using XMLSlurper
// For large document sizes, the memory usage of XMLParser might become
// prohibitive. The class XMLSlurper comes to rescue in these cases.
inputStream = this.getClass().getClassLoader().getResourceAsStream("ch9/languages.xml")
languages = new XmlSlurper().parse(new InputStreamReader(inputStream))
println "Languages and authors"
languages.language.each {
    println "${it.@name} authored by ${it.author[0].text()}"
}
languagesByAuthor = { authorName ->
    languages.language.findAll { it.author[0].text() == authorName }.collect {it.@name}.join(', ')
}
println "Languages by Wirth:" + languagesByAuthor('Wirth')

// using XML namespaces
println '-- using namespaces --'
inputStream = this.getClass().getClassLoader().getResourceAsStream("ch9/computerAndNaturalLanguages.xml")
languages = new XmlSlurper().parse(inputStream).declareNamespace(human: 'Natural')
print "Languages: "
println languages.language.collect { it.@name }.join(', ')
print "Natural languages: "
println languages.'human:language'.collect { it.@name }.join(', ')