// https://www.codewars.com/kata/5ae326342f8cbc72220000d2/train/kotlin

fun main() {
  println(stringExpansion("3abc"))
}

val regex = "\\d*[a-zA-Z]*".toRegex()

fun stringExpansion(s: String): String =
    regex
        .findAll(s)
        .map { it.groupValues.first() }
        .flatMap { match ->
          match.indexOfLast { it.isDigit() }
              .takeUnless { it == -1 }
              ?.let { index ->
                match
                    .substring(index + 1)
                    .map { c ->
                      c.toString().repeat(Character.getNumericValue(match[index]))
                    }
              } ?: listOf(match)
        }
        .joinToString(separator = "")
