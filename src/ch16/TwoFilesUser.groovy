package ch16

class TwoFileUser {
    def useFiles(str) {
        def file1 = new java.io.FileWriter("output1.txt")
        def file2 = new java.io.FileWriter("output2.txt")
        file1.write str
        file2.write str.size()
        file1.close()
        file2.close()
    }
}