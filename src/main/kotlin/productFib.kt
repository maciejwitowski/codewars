// https://www.codewars.com/kata/5541f58a944b85ce6d00006a/train/kotlin
import org.junit.Assert.assertArrayEquals

fun main() {
  val r = longArrayOf(55, 89, 1)
  assertArrayEquals(r, productFib(4895))
}

fun productFib(prod: Long): LongArray {
  var a = 0L
  var b = 1L

  while (a * b < prod) {
    val temp = a
    a = b
    b += temp
  }

  return longArrayOf(a, b, if (a * b == prod) 1 else 0)
}
