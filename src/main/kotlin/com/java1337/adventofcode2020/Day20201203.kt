package com.java1337.adventofcode2020

/**
 *
 * With the toboggan login problems resolved, you set off toward the airport.
 * While travel by toboggan might be easy, it's certainly not safe: there's
 * very minimal steering and the area is covered in trees. You'll need to see
 * which angles will take you near the fewest trees.
 *
 * Due to the local geology, trees in this area only grow on exact integer
 * coordinates in a grid. You make a map (your puzzle input) of the open
 * squares (.) and trees (#) you can see. For example:
 *
 * ..##.......
 * #...#...#..
 * .#....#..#.
 * ..#.#...#.#
 * .#...##..#.
 * ..#.##.....
 * .#.#.#....#
 * .#........#
 * #.##...#...
 * #...##....#
 * .#..#...#.#
 *
 * These aren't the only trees, though; due to something you read about once
 * involving arboreal genetics and biome stability, the same pattern repeats
 * to the right many times:
 *
 * ..##.........##.........##.........##.........##.........##.......  --->
 * #...#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..
 * .#....#..#..#....#..#..#....#..#..#....#..#..#....#..#..#....#..#.
 * ..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#
 * .#...##..#..#...##..#..#...##..#..#...##..#..#...##..#..#...##..#.
 * ..#.##.......#.##.......#.##.......#.##.......#.##.......#.##.....  --->
 * .#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#
 * .#........#.#........#.#........#.#........#.#........#.#........#
 * #.##...#...#.##...#...#.##...#...#.##...#...#.##...#...#.##...#...
 * #...##....##...##....##...##....##...##....##...##....##...##....#
 * .#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#  --->
 *
 * You start on the open square (.) in the top-left corner and need to reach
 * the bottom (below the bottom-most row on your map).
 *
 * The toboggan can only follow a few specific slopes (you opted for a
 * cheaper model that prefers rational numbers); start by counting all the
 * trees you would encounter for the slope right 3, down 1:
 *
 * From your starting position at the top-left, check the position that is
 * right 3 and down 1. Then, check the position that is right 3 and down 1
 * from there, and so on until you go past the bottom of the map.
 *
 * The locations you'd check in the above example are marked here with O
 * where there was an open square and X where there was a tree:
 *
 * ..##.........##.........##.........##.........##.........##.......  --->
 * #..O#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..
 * .#....X..#..#....#..#..#....#..#..#....#..#..#....#..#..#....#..#.
 * ..#.#...#O#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#
 * .#...##..#..X...##..#..#...##..#..#...##..#..#...##..#..#...##..#.
 * ..#.##.......#.X#.......#.##.......#.##.......#.##.......#.##.....  --->
 * .#.#.#....#.#.#.#.O..#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#
 * .#........#.#........X.#........#.#........#.#........#.#........#
 * #.##...#...#.##...#...#.X#...#...#.##...#...#.##...#...#.##...#...
 * #...##....##...##....##...#X....##...##....##...##....##...##....#
 * .#..#...#.#.#..#...#.#.#..#...X.#.#..#...#.#.#..#...#.#.#..#...#.#  --->
 *
 * In this example, traversing the map using this slope would cause you to
 * encounter 7 trees.
 *
 * Starting at the top-left corner of your map and following a slope of
 * right 3 and down 1, how many trees would you encounter?
 *
 */
class Day20201203 {

    data class Row(val input: String) {
        val parsedInput: List<Int> =
            input.toList()
                .mapIndexed { index: Int, c: Char -> if (c == '#') index else -1 }
                .filter { it > -1 }

        fun checkRow(column: Int) : Boolean {
            val columnToCheck = column % input.length
            return (parsedInput.contains(columnToCheck))
        }
    }

    fun countTreesHit(input: List<String>, right: Int) : Int {
        var currentColumn = 0
        return input.map {
            val row = Row(it)
            println("Checking column $currentColumn with input $it")
            val result = if (row.checkRow(currentColumn)) {
                1
            } else {
                0
            }
            currentColumn += right
            result
        }.sum()
    }

    fun countTreesHitAllSlope(input : List<String>) : Int {
        val one_one = countTreesHit(input, 1)
        val three_one = countTreesHit(input, 3)
        val five_one = countTreesHit(input, 5)
        val seven_one = countTreesHit(input, 7)
        val one_two = countTreesHit(input.filterIndexed { index, _ -> index % 2 == 0 }, 1)

        return one_one * three_one * five_one * seven_one * one_two
    }
}

fun main() {
    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    val inputRaw = Day20201203::class.java.classLoader.getResource("Day20201203.txt").readText()
    val input = inputRaw.split("\n")

    val firstOutput = Day20201203().countTreesHit(input, 3)
    println("Trees hit (3,1)= $firstOutput")

    val secondOutput = Day20201203().countTreesHitAllSlope(input)
    println("Trees hit (ALL)= $secondOutput")
}

