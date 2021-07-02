// https://www.codewars.com/kata/54521e9ec8e60bc4de000d6c/train/kotlin
import kotlin.math.max

fun main() {
  println(maxSequence(listOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)))
}

fun maxSequence(arr: List<Int>): Int {
  var maxSoFar = 0
  var maxEndingHere = 0

  for (n in arr) {
    maxEndingHere += n
    if (maxEndingHere < 0) {
      maxEndingHere = 0
    }
    if (maxSoFar < maxEndingHere) {
      maxSoFar = maxEndingHere
    }
  }
  return maxSoFar
}

fun maxrec(arr: List<Int>): Int {
  fun max(arr: List<Int>, maxSoFar: Int, maxEndingHere: Int): Int {
    if (arr.isEmpty()) {
      return maxSoFar
    }

    var endingHere = maxEndingHere + arr.first()
    var soFar = maxSoFar
    if (endingHere < 0) {
      endingHere = 0
    }
    if (soFar < endingHere) {
      soFar = endingHere
    }
    return max(arr.drop(1), soFar, endingHere)
  }
  return max(arr, 0, 0)
}

fun maxStdlib(arr: List<Int>): Int {
  var max = 0
  arr.indices.forEach { outer ->
    (outer..arr.size).forEach { inner ->
      max = max(arr.subList(outer, inner).sum(), max)
    }
  }
  return max
}