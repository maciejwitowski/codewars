import kotlin.test.assertEquals

// https://www.codewars.com/kata/51c8e37cee245da6b40000bd/train/kotlin

fun main() {
  assertEquals("apples, plums\npears\noranges", solution("apples, plums % and bananas\npears\noranges !applesauce", charArrayOf('%', '!')))
  assertEquals("Q\nu\ne", solution("Q @b\nu\ne -e f g", charArrayOf('@', '-')))
}

fun solution(input: String, markers: CharArray): String =
    input.lines().joinToString("\n") { line ->
      markers.fold(line) { acc, char ->
        acc.substringBefore(char).trim()
      }
    }
