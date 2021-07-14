// https://www.codewars.com/kata/51ba717bb08c1cd60f00002f/train/kotlin

import kotlin.test.assertEquals

fun main() {
  assertEquals("-6,-3-1,3-5,7-11,14,15,17-20", rangeExtraction(intArrayOf(-6, -3, -2, -1, 0, 1, 3, 4, 5, 7, 8, 9, 10, 11, 14, 15, 17, 18, 19, 20)))
  assertEquals("-3--1,2,10,15,16,18-20", rangeExtraction(intArrayOf(-3, -2, -1, 2, 10, 15, 16, 18, 19, 20)))
}

fun rangeExtraction(arr: IntArray): String =
    StringBuilder().apply {
      val group = mutableListOf<Int>()
      var previous: Int? = null
      for (a in arr) {
        if (previous != null && (a - previous != 1)) {
          appendGroup(group)
          append(",")
          group.clear()
        }

        group.add(a)
        previous = a
      }

      appendGroup(group)
    }.toString()

private fun StringBuilder.appendGroup(group: List<Int>) {
  when (group.size) {
    0 -> {
    }
    1 -> append("${group.first()}")
    2 -> append(group.joinToString(","))
    else -> append("${group.first()}-${group.last()}")
  }
}

fun rangeExtraction2(
    arr: IntArray
): String = arr.fold(emptyList<Pair<Int, Int>>()) { rs, x ->
  rs.lastOrNull().run { if (this != null && x - second == 1) rs.dropLast(1) + (first to x) else rs + (x to x) }
}.joinToString(",") { (x, y) -> if (y - x > 1) "$x-$y" else (x..y).joinToString(",") }