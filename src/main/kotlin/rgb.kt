import kotlin.test.assertEquals

// https://www.codewars.com/kata/513e08acc600c94f01000001/train/kotlin

fun main() {
  assertEquals("000000", rgb(0, 0, 0))
  assertEquals("000000", rgb(0, 0, -20))
  assertEquals("FFFFFF", rgb(300, 255, 255))
  assertEquals("ADFF2F", rgb(173, 255, 47))
  assertEquals("9400D3", rgb(148, 0, 211))
}

fun rgb(r: Int, g: Int, b: Int): String =
  listOf(r, g, b)
      .map {
        it.toValidRgbDecimal()
      }
      .joinToString("") {
        when (it) {
          0 -> "00"
          else -> Integer.toHexString(it).toUpperCase()
        }
      }

private fun Int.toValidRgbDecimal() = when {
  this < 0 -> 0
  this > 255 -> 255
  else -> this
}