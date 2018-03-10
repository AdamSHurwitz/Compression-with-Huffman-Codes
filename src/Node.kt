import org.jetbrains.annotations.Nullable

class Node(var id: String, var root: String, var weight: Int, var binaryCode: String, var isSaved: Boolean,
           var rightChildId: String, var leftChildId: String): Comparable<Node>{

    override fun equals(other: Any?): Boolean {

        other as Node

        if (id == other.id) return true
        else return false
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + root.hashCode()
        result = 31 * result + weight
        return result
    }

    override fun compareTo(other: Node): Int {
        if (weight > other.weight) {
            return 1
        } else {
            return -1
        }
    }

}