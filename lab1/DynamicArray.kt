class DynamicArray {
    private var array: IntArray

    private var size: Int
    private var finalSize: Int

    private fun inc(factor: Int) { // to expand the length of the array
        val temp = IntArray(finalSize * factor)
        for (i in 0 until finalSize) {
            temp[i] = array[i]
        }
        array = temp
        finalSize *= factor
    }

    private fun trim() { // to delete extra size
        val temp = IntArray(size)
        for (i in 0 until size) {
            temp[i] = array[i]
        }
        array = temp
        finalSize = array.size
    }

    fun add(index: Int = size, element: Int) {
        if (size == finalSize) {
            inc(2)
        }
        if (index == size) {
            for (i in size - 1 downTo index) {
                array[i + 1] = array[i]
            }
        }
        array[index] = element
        size++
        trim()
    }

    fun remove(index: Int) {
        if (index >= size || index < 0) {
            println("Nothing to delete")
        } else {
            for (i in index until size - 1) array[i] = array[i + 1]
            array[size - 1] = 0
            size--
            trim()
        }
    }

    fun get(index: Int) = array[index]

    init {
        array = IntArray(2)
        size = 0
        finalSize = 2
    }
}