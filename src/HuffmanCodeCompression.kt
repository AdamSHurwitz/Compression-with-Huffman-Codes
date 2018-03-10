import java.util.*
import kotlin.collections.HashMap

class HuffmanCodeCompression {
    var nodeMap = HashMap<String, Node>()
    var nodeWeightMinHeap = PriorityQueue<Node>()
    var maxBitcodeLength = 0
    var minBitcodeLength = 100000000
    fun calculate(nodeMap: java.util.HashMap<String, Node>, nodeWeightMinHeap: PriorityQueue<Node>): String {
        this.nodeMap = nodeMap;
        this.nodeWeightMinHeap = nodeWeightMinHeap
        while (nodeWeightMinHeap.size > 1) {
            var leftNode = nodeWeightMinHeap.poll()
            var rightNode = nodeWeightMinHeap.poll()
            mergeNodes(leftNode, rightNode)
        }
        generateChildNodesBinaryCode(nodeWeightMinHeap.poll())
        return String.format("Binarycode Length is min:%1s max:%2s", minBitcodeLength, maxBitcodeLength)
    }

    private fun mergeNodes(leftNode: Node, rightNode: Node) {
        var newNodeId = leftNode.id + rightNode.id
        var rightNodeId = rightNode.id
        var leftNodeId = leftNode.id
        rightNode.root = newNodeId
        leftNode.root = newNodeId
        var newNode = Node(newNodeId, newNodeId, leftNode.weight + rightNode.weight, "",
                false, rightNodeId, leftNodeId)
        nodeMap.put(newNodeId, newNode)
        nodeWeightMinHeap.add(newNode)
    }

    private fun generateChildNodesBinaryCode(rootNode: Node?) {
        var rightChildId = rootNode?.rightChildId
        var leftChildId = rootNode?.leftChildId
        var rightChildNode = nodeMap.get(rightChildId)
        var leftChildNode = nodeMap.get(leftChildId)
        if(rightChildId!!.isNotBlank() && leftChildId!!.isNotBlank()) {
            var rootBinaryCode = rootNode?.binaryCode
            rightChildNode?.binaryCode = rootBinaryCode + 1
            leftChildNode?.binaryCode = rootBinaryCode + 0
            var rightChildBinaryCodeLength = rightChildNode?.binaryCode?.length
            if (rightChildNode?.isSaved ?: false) {
                minBitcodeLength = Math.min(minBitcodeLength, rightChildBinaryCodeLength ?: -1)
                maxBitcodeLength = Math.max(maxBitcodeLength, rightChildBinaryCodeLength ?: -1)
            }
            var leftChildBinaryCodeLength = leftChildNode?.binaryCode?.length
            if (rightChildNode?.isSaved ?: false) {
                minBitcodeLength = Math.min(minBitcodeLength, leftChildBinaryCodeLength ?: -1)
                maxBitcodeLength = Math.max(maxBitcodeLength, leftChildBinaryCodeLength ?: -1)
            }
            generateChildNodesBinaryCode(rightChildNode)
            generateChildNodesBinaryCode(leftChildNode)
        }
    }
}