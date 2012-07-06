package ch9

/**
 * Creating XML documents
 */


langs = ['C++': 'Stroustrup', 'Java': 'Gosling', 'Lisp': 'McCarthy']
content = ''
langs.each {language, author ->
    fragment = """
    <language name = "${language}">
        <author> ${author} </author>
    </language>
    """
    content += fragment
}
xml = "<languages>${content}</languages>"
println xml

// Alternately, you can use the MarkupBuilder or StreamingMarkupBuilder to
// create XML-formatted output of data from an arbitrary source.
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