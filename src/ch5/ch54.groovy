package ch5

/**
 * Closures helping in ''execute around' methods
 */
writer = new FileWriter('output.txt')
writer.write('!')
// this will not save anything in file since you forgot to call writer.close()
// groovy can help you like this:

new FileWriter('output.txt').withWriter {writer ->
    writer.write('a')
}// no need to close()

class Resource {
    def open() {println "open.."}

    def close() {println "close.."}

    def read() {println "read.."}

    def write() {println "write.."}

    // to make sure user won't forget to open and close you can make a closure
    def static use(closure) {
        def r = new Resource()
        try {
            r.open()
            closure(r)
        } finally {
            r.close()
        }
    }
}
Resource.use({ res->
    res.read()
    res.write()
})

