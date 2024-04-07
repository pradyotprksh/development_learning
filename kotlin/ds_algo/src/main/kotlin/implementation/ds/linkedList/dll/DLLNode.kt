package implementation.ds.linkedList.dll

import java.lang.Exception

data class DLLNode<T>(
    var prev: DLLNode<T>?,
    val data: T,
    var next: DLLNode<T>?,
) {
    override fun toString(): String {
        return try {
            "[$data]<->$next"
        } catch (e: Exception) {
            "LOOP DETECTED"
        }
    }

    fun onlyNodeString(): String {
        return "...<-[$data]->..."
    }
}
