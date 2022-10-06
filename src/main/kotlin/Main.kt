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
}