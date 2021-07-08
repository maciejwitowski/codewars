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
  val numbers = n
      .toString()
      .map(Character::getNumericValue)
      .toMutableList()

  var found: Int? = null
  for (i in numbers.lastIndex downTo 1) {
    if (numbers[i - 1] < numbers[i]) {
      found = i - 1
      break
    }
  }

  if (found == null) {
    return -1
  }

  val toSwap = numbers
      .withIndex()
      .filter { it.index > found && it.value > numbers[found] }
      .minByOrNull { indexedValue -> indexedValue.value }!!

  val foundBeforeSwap = numbers[found]
  numbers[found] = toSwap.value
  numbers[toSwap.index] = foundBeforeSwap

  val new = numbers.slice(0..found) + numbers.slice(found + 1..numbers.lastIndex).sorted()

  return new.joinToString(separator = "").toLong()
}

fun nextBiggerNumberOther(n: Long): Long {
  val text = n.toString().toMutableList()
  for (i in text.size - 2 downTo 0) {
    if (text[i] < text[i + 1]) {
      val tail = text.subList(i + 1, text.size)
      val min = tail.withIndex().filter { it.value > text[i] }.minBy { it.value }!!
      text[i + 1 + min.index] = text[i]
      text[i] = min.value
      tail.sort()
      return text.joinToString("").toLong()
    }
  }
  return -1
}