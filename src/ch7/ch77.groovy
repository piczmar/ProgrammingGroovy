package ch7

/**
 * Map convenience methods
 */
langs = ['C++': 'Stroustrup', 'Java': 'Gosling', 'Lisp': 'McCarthy']
print "Does any language name have a nonalphabetic character? "
println langs.any {language, author ->
    language =~ "[^A-Za-z]"
}

print "Do all language names have a nonalphabetic character? "
println langs.every {language, author ->
    language =~ "[^A-Za-z]"
}

friends = [briang = 'Brian Goetz', brians = 'Brian Sletten',
        davidb = 'David Bock', davidg = 'David Geary',
        scottd = 'Scott Davis', scottl = 'Scott Leberknight',
        stuarth = 'Stuart Halloway']
groupByFirstName = friends.groupBy { it.split(' ')[0] }
groupByFirstName.each { firstName, buddies ->
    println "$firstName : ${buddies.join(', ')}"
}
