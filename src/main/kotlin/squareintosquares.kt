import kotlin.test.assertEquals

// https://www.codewars.com/kata/54eb33e5bc1a25440d000891/train/kotlin

fun main() {
  assertEquals("null", Decomp.decompose(2))
  assertEquals("1 2 4 10", Decomp.decompose(11))
  assertEquals("1 2 3 7 9", Decomp.decompose(12))
  assertEquals(Decomp.decompose(4), "null")
}


object Decomp {

  fun decompose(n: Long): String {
    val list = mutableListOf<Long>()
    val square = n * n
    var left = square
    var found = false
    for (x in 1..n) {
      for (i in (n - x) downTo 0) {
        val newLeft = left - i * i

        if (newLeft >= 0L) {
          left = newLeft
          list.add(i)
        }

        if (newLeft == 0L) {
          found = true
          break
        }
      }

      if (found) {
        break
      } else {
        list.clear()
        left = square
      }
    }

    return if (found) {
      list.reversed().joinToString(" ")
    } else {
      "null"
    }
  }
}