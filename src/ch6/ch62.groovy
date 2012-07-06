package ch6

/**
 * String lazy evaluation
 */

price = 568.23
company = 'Google'
quote = "Today $company stock closed at $price"
println quote

stocks = [Apple: 130.01, Microsoft: 35.95]
// you cannot modify immutable objects: company and price, here new references are created
// and quote is bound to old references
stocks.each {key, value ->
    company = key
    price = value
    println quote // so the result is not as expected
}
// to correct it we need to do:
// GString does something special when evaluating expressions—if you have a variable, it prints
// its value to a writer, typically a StringWriter. However, instead of a variable,
// if you have a closure, it invokes the closure. If your closure takes no parameters at all, then it
// simply calls your closure and prints the result you return to the writer.
// If your closure takes more than one parameter, then the call fails with
// an exception.
companyClosure = {it.write(company)}
priceClosure = {it.write("$price")}
quote = "Today ${companyClosure} stock closed at ${priceClosure}"
stocks.each {key, value ->
    company = key
    price = value
    println quote
}

// we can refactor it to look better:
companyClosure = {->company}
priceClosure = {->price}
quote = "Today ${companyClosure} stock closed at ${priceClosure}"
stocks.each {key, value ->
    company = key
    price = value
    println quote
}

// even better, self-contained code:
quote = "Today ${->company} stock closed at ${->price}"
stocks.each {key, value ->
    company = key
    price = value
    println quote
}
