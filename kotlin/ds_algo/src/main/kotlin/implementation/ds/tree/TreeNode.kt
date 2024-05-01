package implementation.ds.tree

data class TreeNode<T>(
    var left: TreeNode<T>?,
    val data: T,
    var right: TreeNode<T>?,
) {
    override fun toString(): String {
        return buildTreeString(this, "", "", StringBuilder()).toString()
    }

    private fun buildTreeString(
        node: TreeNode<T>?,
        prefix: String,
        childrenPrefix: String,
        result: StringBuilder
    ): StringBuilder {
        if (node != null) {
            result.append(prefix)
            result.append(node.data)
            result.append('\n')
            if (node.left != null || node.right != null) {
                if (node.left != null && node.right == null) {
                    buildTreeString(node.left, "$childrenPrefix└── ", "$childrenPrefix    ", result)
                } else if (node.left == null) {
                    buildTreeString(node.right, "$childrenPrefix└── ", "$childrenPrefix│   ", result)
                } else {
                    buildTreeString(node.left, "$childrenPrefix├── ", "$childrenPrefix│   ", result)
                    buildTreeString(node.right, "$childrenPrefix└── ", "$childrenPrefix    ", result)
                }
            }
        }
        return result
    }
}
