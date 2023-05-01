package data_structures.trees

data class PriorityNode(val data: String, val priority: Int) {
    override fun toString(): String {
        return "{$data ^ $priority}"
    }
}

class PriorityQueue {
    var heap = ArrayList<PriorityNode>()
        private set

    fun enqueue(data: String, priority: Int) {
        heap.add(PriorityNode(data = data, priority = priority))

        var dataIndex = heap.size - 1
        var parentIndex = (dataIndex - 1) / 2

        while (parentIndex >= 0 && heap[parentIndex].priority < heap[dataIndex].priority) {
            val temp = heap[parentIndex]
            heap[parentIndex] = heap[dataIndex]
            heap[dataIndex] = temp

            dataIndex = parentIndex
            parentIndex = (parentIndex - 1) / 2
        }
    }

    @Suppress("KotlinConstantConditions")
    fun dequeue(): PriorityNode {
        if (heap.isEmpty()) {
            throw IllegalStateException("Heap is empty")
        }

        if (heap.size == 1) {
            return heap.removeFirst()
        }

        val max = heap.removeAt(0)
        heap.add(0, heap.removeLast())

        var dataIndex = 0
        var leftChildIndex = 2 * dataIndex + 1
        var rightChildIndex = 2 * dataIndex + 2

        while (true) {
            if (leftChildIndex < heap.size && heap[leftChildIndex].priority > heap[dataIndex].priority && heap[leftChildIndex].priority > heap[rightChildIndex].priority) {
                val temp = heap[dataIndex]
                heap[dataIndex] = heap[leftChildIndex]
                heap[leftChildIndex] = temp

                dataIndex = leftChildIndex
            } else if (rightChildIndex < heap.size && heap[rightChildIndex].priority > heap[dataIndex].priority) {
                val temp = heap[dataIndex]
                heap[dataIndex] = heap[rightChildIndex]
                heap[rightChildIndex] = temp

                dataIndex = rightChildIndex
            } else {
                break
            }

            leftChildIndex = (2 * dataIndex) + 1
            rightChildIndex = (2 * dataIndex) + 2
        }

        return max
    }

    fun print() {
        println(heap.toString())
    }
}