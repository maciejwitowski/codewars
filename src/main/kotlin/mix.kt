// https://www.codewars.com/kata/5629db57620258aa9d000014
import kotlin.test.assertEquals

fun main() {
  assertEquals("2:eeeee/2:yy/=:hh/=:rr", mix("Are they here", "yes, they are here"))
  assertEquals("1:ooo/1:uuu/2:sss/=:nnn/1:ii/2:aa/2:dd/2:ee/=:gg",
      mix("looping is fun but dangerous", "less dangerous than coding"))
}

fun mix(s1: String, s2: String): String {
  val a = s1.countMultipleLowerLetters()
  val b = s2.countMultipleLowerLetters()

  return (a.keys + b.keys)
      .map { key ->
        val nA = a[key] ?: 0
        val nB = b[key] ?: 0

        when {
          nA == nB -> null to "$key".repeat(nA)
          nA > nB -> 1 to "$key".repeat(nA)
          else -> 2 to "$key".repeat(nB)
        }
      }
      .map { (i, s) -> "${i ?: "="}:$s" }
      .sortedWith(compareBy({ -it.length }, { it }))
      .joinToString("/")
}

fun String.countMultipleLowerLetters() = this
    .filter { it.isLowerCase() }
    .groupingBy { it }
    .eachCount()
    .filterValues { it > 1 }
