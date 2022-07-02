package linked_list.singly

data class Node(val data: Int, var next: Node?) {
    override fun toString(): String {
        return if (next != null) {
            "[$data|*]"
        } else {
            "[$data|NULL]"
        }
    }
}

data class DoublyNode(var prev: DoublyNode?, val data: Int, var next: DoublyNode?) {
    override fun toString(): String {
        val prevStr = if (prev != null) "*" else "NULL"
        val nextStr = if (next != null) "*" else "NULL"
        return "[$prevStr|$data|$nextStr]"
    }
}