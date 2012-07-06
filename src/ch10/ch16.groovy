package ch10

/**
 * Accessing Microsoft Excel
 */
def ExcelDBCall = "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls, *.xlsx, *.xlsm, *.xlsb)};DBQ=C:/dev/groovy/ProgrammingGroovy/src/ch10/weather.xlsx;READONLY=false";
def EXCEL_DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
Class.forName(EXCEL_DRIVER);

def sql = groovy.sql.Sql.newInstance(ExcelDBCall, EXCEL_DRIVER)
println "City\t\tTemperature"
sql.eachRow('SELECT * FROM [temperatures$]') {
    println "${it.city}\t\t${it.temperature}"
}
