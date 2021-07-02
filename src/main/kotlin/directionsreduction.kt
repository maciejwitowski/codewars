// https://www.codewars.com/kata/550f22f4d758534c1100025a/train/kotlin

fun main() {
  require(dirReducStack(arrayOf("SOUTH", "EAST", "WEST", "NORTH")).isEmpty())
  require(dirReducStack(arrayOf("NORTH", "SOUTH", "SOUTH", "EAST", "WEST", "NORTH", "NORTH")).contentEquals(arrayOf("NORTH")))
}

fun dirReduc(arr: Array<String>): Array<String> {
  val result = arr.toMutableList()
  var i = 1
  while (i <= result.lastIndex) {
    if (i > 0 && setOf(result[i], result[i-1]) in listOf(setOf("NORTH", "SOUTH"), setOf("WEST", "EAST"))) {
      result.removeAt(i)
      result.removeAt(i-1)
      i--
    } else {
      i++
    }
  }
  return result.toTypedArray()
}

fun dirReducStack(arr: Array<String>): Array<String> {
  if (arr.size <= 1) return arr

  val stack = java.util.Stack<String>()
  val oppositeOf = mapOf(
      "WEST" to "EAST", "EAST" to "WEST",
      "SOUTH" to "NORTH", "NORTH" to "SOUTH"
  )

  for (direction in arr) when {
    stack.empty() -> stack.push(direction)
    stack.peek() == oppositeOf[direction] -> stack.pop()
    else -> stack.push(direction)
  }

  return stack.toTypedArray()
}