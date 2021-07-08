// https://www.codewars.com/kata/55983863da40caa2c900004e/train/kotlin
// Algorithm: https://www.geeksforgeeks.org/find-next-greater-number-set-digits/

import kotlin.test.assertEquals

fun main() {
  assertEquals(251678, nextBiggerNumber(218765))
  assertEquals(1243, nextBiggerNumber(1234))
  assertEquals(2071, nextBiggerNumber(2017))
  assertEquals(-1, nextBiggerNumber(4321))
  assertEquals(536479, nextBiggerNumber(534976))
  assertEquals(637167141, nextBiggerNumber(637167114))
  assertEquals(337659948, nextBiggerNumber(337659894)) // 337659489
}

fun nextBiggerNumber(n: Long): Long {
  val numbers = n.toString().toMutableList()
  for (i in numbers.lastIndex - 1 downTo 0) {
    if (numbers[i] < numbers[i + 1]) {
      val tail = numbers.subList(i + 1, numbers.size)
      val min = tail.withIndex().filter { it.value > numbers[i] }.minByOrNull { it.value }!!
      numbers[i + 1 + min.index] = numbers[i]
      numbers[i] = min.value
      tail.sort()
      return numbers.joinToString("").toLong()
    }
  }
  return -1
}
