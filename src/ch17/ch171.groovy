package ch17

/**
 * Groovy provides builders for a number of everyday tasks, including
 * working with XML, HTML, DOM, SAX, Swing, and even Ant.
 *
 * Building XML
 */
bldr = new groovy.xml.MarkupBuilder()
// each nonexistent method on builder is recognized as new child element
bldr.languages {
    language(name: 'C++') { author('Stroustrup')}
    language(name: 'Java') { author('Gosling')}
    language(name: 'Lisp') { author('McCarthy')}
}

// instead of writing to output stream attach writer and read from the map
langs = ['C++': 'Stroustrup', 'Java': 'Gosling', 'Lisp': 'McCarthy']
writer = new StringWriter()
bldr = new groovy.xml.MarkupBuilder(writer)
bldr.languages {
    langs.each { key, value ->
        language(name: key) {
            author(value)
        }
    }
}
println writer

// If your document is large (a few megabytes), you can use StreamingMarkupBuilder, which is kinder in memory usage
// Using StreamingMarkupBuilder, you can declare namespaces, XML comments, and so on, using the builder support property mkp
langs = ['C++': 'Stroustrup', 'Java': 'Gosling', 'Lisp': 'McCarthy']
xmlDocument = new groovy.xml.StreamingMarkupBuilder().bind {
    mkp.xmlDeclaration()
    mkp.declareNamespace(computer: "Computer")
    languages {
        comment << "Created using StreamingMarkupBuilder"
        langs.each { key, value ->
            computer.language(name: key) {
                author(value)
            }
        }
    }
}
println xmlDocument
