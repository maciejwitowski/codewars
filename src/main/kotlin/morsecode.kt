// https://www.codewars.com/kata/decode-the-morse-code

import java.lang.IllegalStateException
import java.lang.UnsupportedOperationException
import kotlin.test.assertEquals

val MorseCode = mapOf(
    "...." to "H",
    "." to "E",
    "-.--" to "Y",
    ".---" to "J",
    "..-" to "U",
    "-.." to "D",
    "..." to "T"
)

fun main() {
  println(decodeBits("111000"))
}

fun decodeBits(bits: String): String {
  val trimmed = bits.trim { it == '0' }
  val unit: Int = TODO()
  val result = trimmed.split("0".repeat(7 * unit)).joinToString("   ") { word ->
    word.split("0".repeat(3 * unit)).joinToString(" ") { character ->
      character.split("0".repeat(unit)).joinToString("") { sign ->
        when (sign) {
          "1".repeat(unit) -> "."
          "1".repeat(3 * unit) -> "-"
          else -> ""//throw UnsupportedOperationException("Unsupported sign: $sign")
        }
      }
    }
  }
  return result
}

fun decodeMorse(code: String): String =
    code.trim().split("   ").joinToString(" ") { word ->
      word.split(" ").joinToString("") { character ->
        MorseCode.getValue(character)
      }
    }
