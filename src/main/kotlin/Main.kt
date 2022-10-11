fun <T : Comparable<T>> selectionSort(arr: List<T>): List<T> {
    val r: MutableList<T> = arr.toMutableList()
    val n = r.size
    for (i in n - 1 downTo 0) {
        var indexToSwap = i
        for (j in i - 1 downTo 0) {
            if (r[j] > r[indexToSwap]) {
                indexToSwap = j
            }
        }
        r[indexToSwap] = r[i].also { r[i] = r[indexToSwap] }
    }
    return r.toList()
}

@JvmName("selectionSortT")
fun <T : Comparable<T>> MutableList<T>.selectionSort(): MutableList<T> {
    for (i in size - 1 downTo 0) {
        var indexToSwap = i
        for (j in i - 1 downTo 0) {
            if (this[j] > this[indexToSwap]) {
                indexToSwap = j
            }
        }
        this[indexToSwap] = this[i].also { this[i] = this[indexToSwap] }
    }
    return this
}

class Node(value: Int) {
    var v: Int
    var l: Node?
    var r: Node?

    init {
        v = value
        l = null
        r = null
    }
}

class Tree {
    private var size = 0
    private var root: Node = Node(0)
    fun findElement(e: Int, v: Node = root): Node? {
        if(isEmpty()) {
            return null
        }
        if(v.v > e) {
            return if(v.l == null) null else findElement(e, v.l!!)
        } else if(v.v < e) {
            return if(v.r == null) null else findElement(e, v.r!!)
        }
        return v
    }
    fun addElement(e: Int, v: Node = root) {
        if(isEmpty()) {
            ++size
            root = Node(e)
            return
        }
        if(v.v > e) {
            if(v.l == null) {
                ++size
                v.l = Node(e)
            } else {
                addElement(e, v.l!!)
            }
        } else if(v.v < e) {
            if (v.r == null) {
                ++size
                v.r = Node(e)
            } else {
                addElement(e, v.r!!)
            }
        } else {
            return
        }
    }

    fun withTwoChildren(v: Node = root): List<Node> {
        if(isEmpty()) {
            return listOf()
        }
        val r = mutableListOf<Node>()
        if(v.l != null && v.r != null) {
            r += v
        }
        if(v.l != null) {
            r += withTwoChildren(v.l!!)
        }
        if(v.r != null) {
            r += withTwoChildren(v.r!!)
        }
        return r
    }

    fun containsElement(e: Int) : Boolean {
        return findElement(e) != null
    }

    fun size(): Int {
        return size
    }

    fun isEmpty(): Boolean {
        return size == 0
    }
}

fun main() {
    val simpleList = mutableListOf(2, 13, 14, 1, 6, 16, 18, 19, 3, 8)
    simpleList.forEach { print("$it ") }
    println()
    selectionSort(simpleList).forEach { print("$it ") }
    println()
    println()
    simpleList.forEach { print("$it ") }
    println()
    simpleList.selectionSort().forEach { print("$it ") }
    println()
    val tree = Tree()
    println("Is the tree currently empty? : ${tree.isEmpty()}")
    var e = readln().toInt()
    while(e != 0) {
        tree.addElement(e)
        e = readln().toInt()
    }
    print("Elements with two children: ")
    tree.withTwoChildren().forEach { print("${it.v} ") }
    println()
    println("The tree currently holds ${tree.size()} elements")
    println("Is the tree currently empty? : ${tree.isEmpty()}")
    println("Element 3 has an index of: ${tree.findElement(3)}")
    println("Does the tree contain an element of 10? : ${tree.containsElement(10)}")
}