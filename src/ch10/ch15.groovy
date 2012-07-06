package ch10

/**
 * Inserting & updating
 */

Ch10.executeSql({ groovy.sql.Sql sql ->
    dataSet = sql.dataSet('weather')
    println "Number of cities : " + sql.rows('SELECT * from weather').size()
    dataSet.add(city: 'Denver', temperature: 19)
    println "Number of cities : " + sql.rows('SELECT * from weather').size()


    temperature = 50
    sql.executeInsert("""INSERT INTO weather (city, temperature)
            VALUES('Oklahoma City', ${temperature}) """)
    println sql.firstRow("SELECT temperature from weather WHERE city='Oklahoma City'")

    sql.execute("UPDATE weather set temperature=70 where city='Oklahoma City'")
    println sql.firstRow("SELECT temperature from weather WHERE city='Oklahoma City'")

})