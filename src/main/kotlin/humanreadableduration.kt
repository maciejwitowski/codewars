import kotlin.test.assertEquals

// https://www.codewars.com/kata/52742f58faf5485cae000b9a/train/kotlin

fun main() {
  assertEquals("1 second", TimeFormatter.formatDuration(1))
  assertEquals("1 minute and 2 seconds", TimeFormatter.formatDuration(62))
  assertEquals("2 minutes", TimeFormatter.formatDuration(120))
  assertEquals("1 hour", TimeFormatter.formatDuration(3600))
  assertEquals("1 hour, 1 minute and 2 seconds", TimeFormatter.formatDuration(3662))
}

object TimeFormatter {
  enum class Units(val single: String, val plural: String, val inSeconds: Long) {
    SECOND("second", "seconds", 1),
    MINUTE("minute", "minutes", 60),
    HOUR("hour", "hours", 60 * 60),
    DAY("day", "days", 60 * 60 * 24),
    YEAR("year", "years", 60 * 60 * 24 * 365)
  }

  fun formatDuration(seconds: Int): String {
    if (seconds == 0) return "now"

    val result = StringBuilder()
    val units = Units.values().reversed()
    units.fold(seconds.toLong()) { acc, unit ->
      val unitValue = acc / unit.inSeconds
      val left = acc - (unitValue * unit.inSeconds)
      if (unitValue > 0) {
        val valuePhrase = if (unitValue == 1L) unit.single else unit.plural
        if (result.isNotEmpty()) {
          if (left == 0L) {
            result.append(" and ")
          } else {
            result.append(", ")
          }
        }

        result.append("$unitValue $valuePhrase")
      }

      left
    }
    return result.toString()
  }
}
