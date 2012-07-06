package ch10

/**
 *  Transform data from DB to XML
 */
Ch10.executeSql({ sql ->
    bldr = new groovy.xml.MarkupBuilder()
    bldr.weather {
        sql.eachRow('SELECT * from weather') {
            city(name: it.city, temperature: it.temperature)
        }
    }
})