import java.lang.Integer.toHexString
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
        .joinToString("") {
          toHexString(it.coerceIn(0..255))
              .toUpperCase()
              .padStart(2, '0')
        }

fun rgbFormatted(r: Int, g: Int, b: Int) = String.format("%02X%02X%02X", r.coerceIn(0..255), g.coerceIn(0..255), b.coerceIn(0..255))
