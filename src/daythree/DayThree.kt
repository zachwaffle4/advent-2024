package daythree

import java.io.File

typealias Mul = Pair<Int, Int>

val input = File("src/daythree/input.txt").readLines()
const val test = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"

fun main() {
    println(PartOne(input.toString()))
    println(PartTwo(input.toString()))
}

object PartOne {
    fun extractMuls(from: String): List<Mul> = from
        .allIndicesOf("mul(")
        .map {
            from.substring(it, (from.indexOf(")", it) + 1).let { i-> if (i <= 0) from.length else i })
                .replace("mul(", "")
                .replace(")", "")
                .split(",")
        }.filter {
            it.size == 2 && it[0].isInteger() && it[1].isInteger()
        }.map {
            it[0].toInt() to it[1].toInt()
        }

    operator fun invoke(string: String) = extractMuls(string).sumOf { it.multiply() }
}

object PartTwo {
    fun extractMuls(from: String) = from
        .split("do()")
        .map { it.split("don't()", limit=2)[0] }
        .map { PartOne.extractMuls(it) }

    operator fun invoke(string: String) = extractMuls(string).sumOf { muls ->
        muls.sumOf { it.multiply() }
    }
}

fun Mul.multiply() = first * second

fun String.contains(sequence: CharSequence, startPoint: Int) = this.substring(startPoint).contains(sequence)

fun String.isInteger() = this.isNotBlank() && this.all { it.isDigit() }

fun String.allIndicesOf(sequence: CharSequence) = this.indices.filter { this.startsWith(sequence, it) }