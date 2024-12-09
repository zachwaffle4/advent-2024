package daytwo

import daytwo.PartOne.isValidReport
import java.io.File
import kotlin.math.abs

val input = File("src/daytwo/input.txt")
    .readLines()
    .map { line ->
        line.split(" ")
            .map { it.toInt() }
        }

fun main() {
    println(PartOne.reports.size)
    println(PartTwo.reports.size)
}

object PartOne {
    fun isValidReport(report: List<Int>) : Boolean {
        return report.isSorted() && ((0..<report.size-1).all {i ->
            abs(report[i]-report[i+1]) in 1..3
        })
    }

    val reports = input
        .filter { isValidReport(it) }
}

object PartTwo {
    val reports = input.filter {report ->
        PartOne.isValidReport(report) or report.indices.any {
            isValidReport(report.except(it))
        }
    }
}

fun <E> List<E>.except(i: Int) = this.subList(0, i) + this.subList(i+1, size)

fun <E : Comparable<E>> List<E>.isSorted() : Boolean {
    return (this == this.sorted()) or (this == this.sortedDescending())
}
