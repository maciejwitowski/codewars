import kotlin.test.assertEquals

// https://www.codewars.com/kata/5672682212c8ecf83e000050

fun main() {
  assertEquals(dblLinear(10), 22)
  assertEquals(dblLinear2(20), 57)
  assertEquals(dblLinear2(30), 91)
}

fun dblLinear(n: Int): Int {
  val list = mutableListOf(1)
  var x = 0
  var y = 0

  while(list.size <= n) {
    val a = 2 * list[x] + 1
    val b = 3 * list[y] + 1

    when {
      a > b -> {
        list.add(b)
        y++
      }
      a < b -> {
        list.add(a)
        x++
      }
      else -> {
        list.add(a)
        x++
        y++
      }
    }
  }
  return list[n]
}

fun dblLinear2(n: Int) = with(sortedSetOf(1)) {
  for(i in 1..n) {
    val x = pollFirst()
    add((x * 2) + 1)
    add((x * 3) + 1)
  }
  first()
}