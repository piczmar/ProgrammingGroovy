package ch10

import groovy.sql.DataSet

/**
 * Using DataSet
 *
 * When you invoke findAll( ), the
 * DataSet is further refined with a specialized query based on the select
 * predicate you provide. The actual data is still not fetched until you call
 * the each( ) method on the resulting object. As a result, DataSet is highly
 * efficient, bringing only data that is actually selected
 */

// for any reason it does not work: ??
Ch10.executeSql({ sql ->
    dataSet = sql.dataSet('weather')
    citiesBelowFreezing = dataSet.findAll({
        println it
        return true
    })
    println "Cities below freezing:"+citiesBelowFreezing.getClass().name
    citiesBelowFreezing.each {
        //println it.city
    }
})
