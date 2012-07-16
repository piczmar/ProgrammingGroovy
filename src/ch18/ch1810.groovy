package ch18

/**
 * ExpandoMetaClass and DSLs
 */

Integer.metaClass.getDays = {->
    delegate
}
Integer.metaClass.getAgo = {->
    def time = Calendar.getInstance()
    time.add(Calendar.DAY_OF_MONTH, -delegate)
    time
}
Calendar.metaClass.at = {Map map ->
    def hour = 0
    def min = 0
    map.each {k, v ->
        hour = k.toInteger()
        min = v.toInteger()
    }
    delegate.set(Calendar.HOUR_OF_DAY, hour)
    delegate.set(Calendar.MINUTE, min)
    delegate.set(Calendar.SECOND, 0)
    delegate.time
}
println 2.days.ago.at(2:40)
