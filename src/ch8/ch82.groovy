package ch8

import java.util.regex.Matcher

/**
 * Other Groovy JDK extensions
 */
// array extensions
int[] arr = [1, 2, 3, 4, 5, 6]
println arr[2..4]

println System.getProperty("os.name")
if (System.getProperty("os.name").toLowerCase().contains("windows")) {
    // process executing
    process = "find /c Let".execute()
    process.out.withWriter {
        // Send input to process
        it << "Let the World know...\n"
        it << "Groovy Rocks!\n"
    }
    println process.errorStream.text
    // Read output from process
    println process.in.text
    // or
    //println process.text
} else {// if UNIX
    // process executing
    process = "wc".execute()
    process.out.withWriter {
        // Send input to process
        it << "Let the World know...\n"
        it << "Groovy Rocks!\n"
    }
    // Read output from process
    println process.in.text
// or
//println process.text

}

String[] cmd = ['groovy', '-e', "print 'Groovy!'"]
println "Calling ${cmd.join(' ')}"
println cmd.execute().text

// You can start a Thread and provide it a closure that will be run in a separate thread
// using the start( ) method. If you want that thread to be daemon thread,8
// use the startDaemon( ) method instead
def printThreadInfo(msg) {
    def currentThread = Thread.currentThread()
    println "$msg Thread is $currentThread. Daemon? ${currentThread.isDaemon()}"
}

printThreadInfo('Main')

Thread.start {
    printThreadInfo("Started")
    sleep(3000) {println "Interrupted"}
    println "Finished Started"
}
sleep(1000)
// A daemon thread quits if there are no active nondaemon threads currently running
Thread.startDaemon {
    printThreadInfo("Started Daemon")
    sleep(5000) {println "Interrupted"}
    println "Finished Started Daemon"
}
// The daemon thread in the previous example was aborted as soon as the
// main thread and the nondaemon thread you created quit

// java.io Extensions
println new File('thoreau.txt').text

// Instead of reading the entire file in one shot, if you want to read and process one line at a time
new File('thoreau.txt').eachLine {
    println it
}

// If you want to fetch only those lines of text that meet a certain condition
println new File('thoreau.txt').filterLine {it =~ /life/}

// java.util Extensions
new Timer().runAfter(10000) {
    println "Actually executed at ${new Date()}."
}
int delay = 1000 //delay in milliseconds before task is to be executed
int period = 5000 //time in milliseconds between successive task executions.
new Timer().schedule ({ println "Timer task executed at ${new Date()}"} as TimerTask, 1000, 5000 )
println "Current date is ${new Date()}."

lst = [1,2,3]
syncLst = lst.asSynchronized()

immutableLst = lst.asImmutable()

str = "thi si some string"
matcher = str =~ /thi/
println matcher[0]

// groovy socket programming http://programmingitch.blogspot.com/2010/04/groovy-sockets-example.html
