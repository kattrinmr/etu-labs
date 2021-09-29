package stack

class Stack {
    private val data = mutableListOf<String>()
    private val length = data.size

    fun push(new: String) = data.add(new)
    fun pop() = data.removeAt(data.lastIndex)
    fun isEmpty() = length == 0
}

