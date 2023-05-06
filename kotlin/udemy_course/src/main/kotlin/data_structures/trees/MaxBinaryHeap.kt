package data_structures.trees

class MaxBinaryHeap {
    // Formula
    //
    // Child of parent
    // left = 2n + 1
    // right = 2n + 2
    //
    // Parent of the child
    // parent = (n - 1) / 2

    var heap = ArrayList<Int>()
        private set

    fun insert(data: Int) {
        heap.add(data)

        var dataIndex = heap.size - 1
        var parentIndex = (dataIndex - 1) / 2

        while (parentIndex >= 0 && heap[parentIndex] < heap[dataIndex]) {
            val temp = heap[parentIndex]
            heap[parentIndex] = heap[dataIndex]
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

        if (heap.size == 1) {
            return heap.removeFirst()
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

    fun height(): Int {
        var height = 0

        var lastChildIndex = heap.size - 1
        var parentIndex = (lastChildIndex - 1) / 2

        while (parentIndex > 0) {
            ++height

            lastChildIndex = parentIndex
            parentIndex = (lastChildIndex - 1) / 2
        }

        return ++height
    }

    fun print() {
        println(heap.toString())
    }
}