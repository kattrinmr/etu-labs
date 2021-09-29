
class Node(var data: Int, var next: Node? = null) // single linked list

fun insert(prev: Node?, new: Node?) { // push
    new?.next = prev?.next
    prev?.next = new
}

fun remove(head: Node?, removal: Node?) { // pop
    if (head === removal)
        head?.next = null
    else {
        var node: Node? = head
        while (node != null) {
            if (node.next === removal) {
                val next = removal?.next
                removal?.next = null
                node.next = next
                return
            }
            node = node.next
        }
    }
}
