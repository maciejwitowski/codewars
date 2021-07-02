// https://www.codewars.com/kata/55bf01e5a717a0d57e0000ec
import kotlin.test.assertEquals

fun main() {
  assertEquals(3, persistence(39))
  assertEquals(0, persistence(4))
  assertEquals(2, persistence(25))
  assertEquals(4, persistence(999))
}

fun persistence(num: Int): Int {
  var result = 0
  var number = num

  while (number >= 10) {
    number = number
        .toString()
        .map(Character::getNumericValue)
        .reduce { acc, i -> acc * i }
    result += 1
  }
  return result
}

fun persistenceUsingSequence(num: Int) = generateSequence(num) {
  it.toString().map(Character::getNumericValue).reduce { mult, element -> mult * element }
}.takeWhile { it > 9 }.count()