class AvlTree {

    private class Node(var key: Int, var parent: Node?) {
        var balance = 0
        var left: Node? = null
        var right: Node? = null
    }

    private var root: Node? = null

    fun insert(key: Int) {
        TODO()
    }

    fun delete(toDelKey: Int) {
        if (root == null) return

        var n: Node? = root
        var parent: Node? = root
        var delNode: Node? = null
        var child: Node? = root

        while (child != null) {
            parent = n
            n = child
            child = if (toDelKey >= n.key) n.right else n.left
            if (toDelKey == n.key) delNode = n
        }

        if (delNode != null) {
            delNode.key = n!!.key
            child = if (n.left != null) n.left else n.right

            if (root!!.key.compareTo(toDelKey) == 0) {
                root = child
                if (root != null) root!!.parent = null
            } else {
                if (parent!!.left == n) parent.left = child
                else parent.right = child
                if (null != child) child.parent = parent
                balance(parent)
            }
        }
    }

    private fun height(n: Node?): Int {
        if (n == null) return -1
        return height(n.left).coerceAtLeast(height(n.right)) + 1
    }

    private fun setBalance(vararg ns: Node) {
        for (n in ns) n.balance = height(n.right) -  height(n.left)
    }

    private fun rotateLeft(n: Node): Node {
        val nn: Node? = n.right
        nn!!.parent = n.parent
        n.right = nn.left

        if (n.right != null) n.right!!.parent = n

        nn.left = n
        n.parent = nn

        if (nn.parent != null) {
            if (nn.parent!!.right == n) nn.parent!!.right = nn
            else nn.parent!!.left = nn
        }

        setBalance(n, nn)
        return nn
    }

    private fun rotateRight(n: Node): Node {
        val nn: Node? = n.left
        nn!!.parent = n.parent
        n.left = nn.right

        if (n.left != null) n.left!!.parent = n

        nn.right = n
        n.parent = nn

        if (nn.parent != null) {
            if (nn.parent!!.right == n) nn.parent!!.right = nn
            else nn.parent!!.left = nn
        }

        setBalance(n, nn)
        return nn
    }

    private fun balance(n: Node) {
        setBalance(n)
        var nn = n
        if (nn.balance == -2)
            if (height(nn.left!!.left) >= height(nn.left!!.right)) nn = rotateRight(nn)
            else {
                nn.left = rotateLeft(n.left!!)
                nn = rotateRight(nn)
            }
        else if (nn.balance == 2)
            if (height(nn.right!!.right) >= height(nn.right!!.left)) nn = rotateLeft(nn)
            else {
                nn.right = rotateRight(n.right!!)
                nn = rotateLeft(nn)
            }
        if (nn.parent != null) balance(nn.parent!!)
        else root = nn
    }
}
