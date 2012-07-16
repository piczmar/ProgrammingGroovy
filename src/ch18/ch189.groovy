package ch18

/**
 * Categories and DSLs
 */
class DateUtil {
    static int getDays(Integer self) { self }

    static Calendar getAgo(Integer self) {
        def date = Calendar.instance
        date.add(Calendar.DAY_OF_MONTH, -self)
        date
    }

    static Date at(Calendar self, Double time) {
        def hour = (int) (time.doubleValue())
        def minute = (int) (Math.round((time.doubleValue() - hour) * 100))
        self.set(Calendar.HOUR_OF_DAY, hour)
        self.set(Calendar.MINUTE, minute)
        self.set(Calendar.SECOND, 0)
        self.time
    }
}
use(DateUtil) {
    println 2.days.ago.at(4.30)
}

// --- version with Map
class DateUtil1 {
    static int getDays(Integer self) { self }

    static Calendar getAgo(Integer self) {
        def date = Calendar.instance
        date.add(Calendar.DAY_OF_MONTH, -self)
        date
    }

    static Date at(Calendar self, Map time) {
        def hour = 0
        def minute = 0
        time.each {key, value ->
            hour = key.toInteger()
            minute = value.toInteger()
        }
        self.set(Calendar.HOUR_OF_DAY, hour)
        self.set(Calendar.MINUTE, minute)
        self.set(Calendar.SECOND, 0)
        self.time
    }
}
use(DateUtil1) {
    println 2.days.ago.at(4:30)
}
