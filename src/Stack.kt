package stack

// Stack is based on standard kotlin list

class Stack {
    private val data = mutableListOf<String>()
    val size get() = data.size

    fun push(new: String) = data.add(new)
    fun pop() = data.removeAt(data.lastIndex)
}

