package ch5

/**
 * Passing parameters to closures
 */
def tellFortune(closure) {
    closure new Date("11/15/2007"), "Your day is filled with ceremony"
}

tellFortune({
    date, fortune ->
    println "Fortune for ${date} is '${fortune}'"
})

// since groovy supports optional typing you can do so:
tellFortune({ Date date, fortune ->
    println "Fortune for ${date} is '${fortune}'"
})
