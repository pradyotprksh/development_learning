package data_structures.trees

class MaxBinaryHeap {
    var heap = ArrayList<Int>()
        private set

    fun insert(data: Int) {
        heap.add(data)

        var dataIndex = heap.size - 1
        var parentIndex = (dataIndex - 1) / 2

        while (parentIndex >= 0 && heap[parentIndex] < data) {
            val temp = heap[parentIndex]
            heap[parentIndex] = data
            heap[dataIndex] = temp

            dataIndex = parentIndex
            parentIndex = (parentIndex - 1) / 2
        }
    }

    @Suppress("KotlinConstantConditions")
    fun remove(): Int {
        if (heap.isEmpty()) {
            throw IllegalStateException("Heap is empty")
        }

        val max = heap.removeAt(0)
        heap.add(0, heap.removeLast())

        var dataIndex = 0
        var leftChildIndex = 2 * dataIndex + 1
        var rightChildIndex = 2 * dataIndex + 2

        while (true) {
            if (leftChildIndex < heap.size && heap[leftChildIndex] > heap[dataIndex] && heap[leftChildIndex] > heap[rightChildIndex]) {
                val temp = heap[dataIndex]
                heap[dataIndex] = heap[leftChildIndex]
                heap[leftChildIndex] = temp

                dataIndex = leftChildIndex
            } else if (rightChildIndex < heap.size && heap[rightChildIndex] > heap[dataIndex]) {
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