fun divisionBySubtraction(dividend: Int, divisor: Int): Int {
    if (divisor == 0) {
        throw IllegalArgumentException("Cannot divide by zero")
    }

    if (dividend < divisor) {
        return 0
    } else {
        return 1 + divisionBySubtraction(dividend - divisor, divisor)
    }
}

fun main() {
    val dividend = 12
    val divisor = 3
    val result = divisionBySubtraction(dividend, divisor)
    println("$dividend / $divisor = $result")
}