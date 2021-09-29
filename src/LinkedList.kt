class Node(var data: String, var next: Node? = null) {

    fun insert(prev: Node?, new: Node?) {
        new?.next = prev?.next
        prev?.next = new
    }

    fun remove(head: Node?, removal: Node?) {
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
}
