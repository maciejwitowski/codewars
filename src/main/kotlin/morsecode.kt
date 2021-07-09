// https://www.codewars.com/kata/decode-the-morse-code

import kotlin.test.assertEquals

val MorseCode = mapOf(
    "...." to "H",
    "." to "E",
    "-.--" to "Y",
    ".---" to "J",
    "..-" to "U",
    "-.." to "D",
)

fun main() {
  assertEquals("HEY JUDE", decodeMorse(".... . -.--   .--- ..- -.. ."))
  assertEquals("E", decodeMorse(" . "))
}

fun decodeMorse(code: String): String =
    code.trim().split("   ").joinToString(" ") { word ->
      word.split(" ").joinToString("") { character ->
        MorseCode.getValue(character)
      }
    }
