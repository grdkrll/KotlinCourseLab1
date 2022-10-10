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
    var l: Int?
    var r: Int?

    init {
        v = value
        l = null
        r = null
    }
}

class Tree {
    private var tree = mutableListOf<Node>()
    fun findElement(e: Int): Int {
        if(tree.isEmpty()) {
            return -1
        }
        var i = 0
        while (tree[i].v != e && (tree[i].v > e && tree[i].l != null || tree[i].v < e && tree[i].r != null)) {
            i = when (tree[i].v > e) {
                true -> tree[i].l!!
                false -> tree[i].r!!
            }
        }
        return if(tree[i].v == e) {
            i
        } else {
            -1
        }
    }
    fun addElement(e: Int) {
        if(tree.isEmpty()) {
            tree.add(Node(e))
            return
        }
        var i = 0
        while (tree[i].v != e && (tree[i].v > e && tree[i].l != null || tree[i].v < e && tree[i].r != null)) {
            i = when (tree[i].v > e) {
                true -> tree[i].l!!
                false -> tree[i].r!!
            }
        }
        if (tree[i].v > e) {
            tree[i].l = tree.size
        } else if (tree[i].v < e) {
            tree[i].r = tree.size
        } else {
            return
        }
        tree.add(Node(e))
    }

    fun withTwoChildren(): List<Int> {
        val r = mutableListOf<Int>()
        for (e in tree) {
            if (e.l != null && e.r != null) {
                r.add(e.v)
            }
        }
        r.selectionSort()
        return r.toList()
    }

    fun containsElement(e: Int) : Boolean {
        return findElement(e) != -1
    }

    fun size(): Int {
        return tree.size
    }

    fun isEmpty(): Boolean {
        return tree.isEmpty()
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
    tree.withTwoChildren().forEach { println(it) }
    println("The tree currently holds ${tree.size()} elements")
    println("Is the tree currently empty? : ${tree.isEmpty()}")
    println("Element 3 has an index of: ${tree.findElement(3)}")
    println("Does the tree contain an element of 10? : ${tree.containsElement(10)}")
}