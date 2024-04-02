package implementation.ds.linkedList.sll

import java.lang.Exception

data class SLLNode<T>(
    val data: T,
    var next: SLLNode<T>?
) {
    override fun toString(): String {
        return try {
            "[$data]->$next"
        } catch (e: Exception) {
            "LOOP DETECTED"
        }
    }

    fun onlyNodeString(): String {
        return "[$data]->..."
    }
}