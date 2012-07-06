package ch10

/**
 * GSQL
 * GSQL is a wrapper around JDBC that provides a number of convenience
 * methods to access data. You can easily create SQL queries and
 * then use built-in iterators to traverse the results. The examples in this
 * chapter use MySQL; however, you can use any database that you can
 * access using JDBC
 * http://www.ibm.com/developerworks/java/library/j-pg01115/index.html
 */

// using derby embedded db
@Grapes([
@Grab(group = 'org.apache.derby', module = 'derby', version = '10.9.1.0')
])
class Ch10 {
    static def executeSql(closure) {

        def driver = "org.apache.derby.jdbc.EmbeddedDriver";
        Class.forName(driver).newInstance();

        def protocol = 'jdbc:derby:'
        def props = new Properties()
//props.put('user', 'user1')
//props.put('password', 'user1')

        def sql = groovy.sql.Sql.newInstance("${protocol}derbyDB;create=true", props)

        println "DB name: ${sql.connection.catalog}"

/* Creating table, adding few lines, updating one */
        try {
            sql.execute('drop table weather')
        } catch (exc) {}

        sql.execute("""
    create table weather (
    city varchar(100) not null,
    temperature integer not null
)
""")


        def inputStream = Ch10.class.getClassLoader().getResourceAsStream("ch10/load_data.sql")
        inputStream.eachLine { line ->
            println line
            sql.execute(line)
        }

        if (closure)
            closure(sql)

        try {
            java.sql.DriverManager.getConnection('jdbc:derby:;shutdown=true')
        }
        catch (java.sql.SQLException se) {

        }

        println('Finish!')

    }
}
Ch10.executeSql()