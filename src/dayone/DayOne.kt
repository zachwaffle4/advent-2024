package dayone

import java.io.File
import kotlin.math.abs

val INPUT_FILE = "src/input.txt"

fun main() {
    partOne()
    partTwo()
}

fun partOne() {
    val (left, right) = createLists()
    left.sort()
    right.sort()
    val distances = left.zip(right).map { (l, r) -> abs(r - l) }
    val result = distances.sum()
    println(result)
}

fun createLists(): Pair<MutableList<Int>, MutableList<Int>> {
    val input = File(INPUT_FILE)
    val reader = input.inputStream().bufferedReader()
    val left = mutableListOf<Int>()
    val right = mutableListOf<Int>()

     reader.forEachLine {
         val nums = it.split(" ")
         left.add(nums[0].toInt())
         right.add(nums[3].toInt())
     }

    return Pair(left, right)
}

fun partTwo() {
    val (left, right) = createLists()
    left.sort()
    right.sort()
    val counts = left.map { l -> l * right.count { r -> r == l } }
    val result = counts.fold(0) { acc, i -> acc + i }
    println(result)
}