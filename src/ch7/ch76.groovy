package ch7

/**
 * Iterating over map
 */
langs = ['C++': 'Stroustrup', 'Java': 'Gosling', 'Lisp': 'McCarthy']
langs.each { entry ->
    println "Language $entry.key was authored by $entry.value"
}

langs.each { language, author ->
    println "Language $language was authored by $author"
}

// collect method
println langs.collect { language, author ->
    language.replaceAll("[+]", "P")
}

println langs.collect { entry ->
    entry.key.replaceAll("[+]", "P")
}

println "Looking for the first language with name greater than 3 characters"
entry = langs.find { language, author ->
    language.size() > 3
}
println "Found $entry.key written by $entry.value"

println "Looking for all languages with name greater than 3 characters"
selected = langs.findAll { language, author ->
    language.size() > 3
}
selected.each { key, value ->
    println "Found $key written by $value"
}