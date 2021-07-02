import java.math.BigDecimal

fun balanceStatements(balance: String): String {
    val parsed = balance.split(",").map { b ->
        b.split(" ").let {
            val quote = it[0]
            val quantity = try {
                it[1].toInt()
            } catch (e: NumberFormatException) {
                null
            }
            val price = try {
                BigDecimal(it[2])
            } catch (e: NumberFormatException) {
                null
            }
            val status = when (it[3]) {
                "B" -> Status.BUY
                "S" -> Status.SELL
                else -> null
            }

            if (listOf(quantity, price, status).any { it == null }) {
                Parsed.Error(b)
            } else {
                Parsed.Order(
                        quote = quote,
                        quantity = quantity!!,
                        price = price!!,
                        status = status!!
                )
            }
        }
    }

    val orders = parsed.filterIsInstance<Parsed.Order>()
    val buy = orders.filter { it.status == Status.BUY }.sumBy { it.price.multiply(BigDecimal(it.quantity)).toInt() }
    val sell = orders.filter { it.status == Status.SELL }.sumBy { it.price.multiply(BigDecimal(it.quantity)).toInt() }
    val statement = "Buy: $buy, Sell: $sell"

    val errors = parsed.filterIsInstance<Parsed.Error>()
    return if (errors.isNotEmpty()) {
        "$statement; Badly formed ${errors.size}: ${errors.joinToString(separator = " ;") { it.message }} ;"
    } else {
        statement
    }
}

sealed class Parsed {
    data class Order(
            val quote: String,
            val quantity: Int,
            val price: BigDecimal,
            val status: Status
    ) : Parsed()

    data class Error(val message: String) : Parsed()
}

enum class Status {
    BUY, SELL
}
