package stack

// stack is based on standard kotlin single list

class Stack {
    private val data = mutableListOf<Char>()

    fun push(new: Char) = data.add(new)
    fun pop() = data.removeAt(data.lastIndex)
    fun peek() = data[0]
    fun isEmpty() = data.size == 0

}