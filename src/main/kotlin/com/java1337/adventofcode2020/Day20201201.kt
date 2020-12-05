package com.java1337.adventofcode2020

/**
 * Before you leave, the Elves in accounting just need you to fix your
 * expense report (your puzzle input); apparently, something isn't quite
 * adding up.
 *
 * Specifically, they need you to find the two entries that sum to 2020
 * and then multiply those two numbers together.
 *
 * For example, suppose your expense report contained the following:
 *
 * 1721
 * 979
 * 366
 * 299
 * 675
 * 1456
 * In this list, the two entries that sum to 2020 are 1721 and 299.
 * Multiplying them together produces 1721 * 299 = 514579, so the correct
 * answer is 514579.
 *
 * Of course, your expense report is much larger. Find the two entries that
 * sum to 2020; what do you get if you multiply them together?
 *
 * --- Part Two ---
 *
 * The Elves in accounting are thankful for your help; one of them even
 * offers you a starfish coin they had left over from a past vacation.
 * They offer you a second one if you can find three numbers in your expense
 * report that meet the same criteria.
 *
 * Using the above example again, the three entries that sum to 2020 are 979,
 * 366, and 675. Multiplying them together produces the answer, 241861950.
 *
 * In your expense report, what is the product of the three entries that sum
 * to 2020?


 */
class Day20201201(val input: List<Int>) {

    fun productForTwoInputsThatSumTo2020(): Int? {
        for (i in input)
            for (j in input)
                if (i + j == 2020)
                    return i * j
        return null

    }

    fun productForThreeInputsThatSumTo2020() : Int? {
        for (i in input)
            for (j in input)
                for (k in input)
                    if (i + j + k == 2020)
                        return i * j * k
        return null
    }
}


fun main() {
    val input = Utils.readFileAsListOfString("Day20201201.txt").map { it.toInt() }
    val obj = Day20201201(input)

    when (val maybeResult: Int? = obj.productForTwoInputsThatSumTo2020()) {
        null -> println("There's no result for this input")
        else -> println("Found a product of two of $maybeResult")
    }

    when (val maybeResult: Int? = obj.productForThreeInputsThatSumTo2020()) {
        null -> println("There's no result for this input")
        else -> println("Found a product of three of $maybeResult")
    }}