package ch5

/**
 * Curried closures
 */

def tellFortunes(closure) {
    Date date = new Date("11/15/2007")
//closure date, "Your day is filled with ceremony"
//closure date, "They're features, not bugs"

// You can curry to avoid sending date repeatedly
//    You can curry any number of parameters, but you can curry only leading
//    parameters. So if you have n parameters, you can curry any of the
//    first k parameters, where 0 <= k <= n.
    postFortune = closure.curry(date)
    postFortune "Your day is filled with ceremony"
    postFortune "They're features, not bugs"
}

tellFortunes() { date, fortune ->
    println "Fortune for ${date} is '${fortune}'"
}
