import java.io.File
import java.util.*

object HuffmanCodeCompressionTest {
    @JvmStatic
    fun main(args: Array<String>){
        var i: Long = 0
        var mergedNodesMap = HashMap<String, Node>()
        var weightsHeap = PriorityQueue<Node>()
        //File("src/data/HuffmanCodeCompressionData_1_10.txt")
        //File("src/data/HuffmanCodeCompressionData_2_10.txt")
        //File("src/data/HuffmanCodeCompressionData_3_10.txt")
        //File("src/data/HuffmanCodeCompressionData_5_20.txt")
        //File("src/data/HuffmanCodeCompressionData_19_160.txt")
        //File("src/data/HuffmanCodeCompressionData_24_320.txt")
        //File("src/data/HuffmanCodeCompressionData_32_1000.txt")
        File("src/data/HuffmanCodeCompressionData.txt")
        //todo: discrepancy between beaunus tests: https://github.com/beaunus/stanford-algs/blob/master/testCases/course3/assignment3HuffmanAndMWIS/question1And2/output_random_40_4000.txt
        //File("src/data/HuffmanCodeCompressionData_40_4000.txt")
                .inputStream()
                .bufferedReader()
                .forEachLine { it ->
                    if(i > 0) {
                        var idString = i.toString()
                        mergedNodesMap.put(idString, Node(idString, idString, it.toInt(), "", true
                        , "", ""))
                        weightsHeap.add(Node(idString, idString, it.toInt(), "", true,
                                "", ""))
                    }
                    i++
                }
        println(HuffmanCodeCompression().calculate(mergedNodesMap, weightsHeap))
    }
}