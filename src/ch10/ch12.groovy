package ch10

/**
 * SQL inserts
 */

Ch10.executeSql({ sql ->


    println "City Temperature"
    sql.eachRow('SELECT * from weather') {
        printf "%-20s%s\n", it.city, it[1]
    }

    // get metadata info
    processMeta = { metaData ->
        metaData.columnCount.times { i ->
            printf "%-21s", metaData.getColumnLabel(i + 1)
        }
        println ""
    }
    // The closure for metadata is called
    // only once after the execution of the SQL statement
    sql.eachRow('SELECT * from weather', processMeta) {
        printf "%-20s %s\n", it.city, it[1]
    }

    // If you want to process all the rows but don’t want to use an iterator, you
    // can use the rows( ) method on the Sql instance
    rows = sql.rows('SELECT * from weather')
    println "Weather info available for ${rows.size()} cites"

//    def val = sql.execute("select * from weather where city = ?", ["Santa Fe"])
//    println "Temperature in Santa Fe is ${val.firstRow()}"
})

