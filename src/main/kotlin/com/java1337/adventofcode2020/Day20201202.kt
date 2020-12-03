package com.java1337.adventofcode2020

import java.io.File

/**
 * Your flight departs in a few days from the coastal airport; the easiest
 * way down to the coast from here is via toboggan.
 *
 * The shopkeeper at the North Pole Toboggan Rental Shop is having a bad
 * day. "Something's wrong with our computers; we can't log in!" You ask
 * if you can take a look.
 *
 * Their password database seems to be a little corrupted: some of the
 * passwords wouldn't have been allowed by the Official Toboggan Corporate
 * Policy that was in effect when they were chosen.
 *
 * To try to debug the problem, they have created a list (your puzzle input)
 * of passwords (according to the corrupted database) and the corporate
 * policy when that password was set.
 *
 * For example, suppose you have the following list:
 *
 * 1-3 a: abcde
 * 1-3 b: cdefg
 * 2-9 c: ccccccccc
 * Each line gives the password policy and then the password. The password policy indicates the lowest and highest number of times a given letter must appear for the password to be valid. For example, 1-3 a means that the password must contain a at least 1 time and at most 3 times.
 *
 * In the above example, 2 passwords are valid. The middle password, cdefg, is not; it contains no instances of b, but needs at least 1. The first and third passwords are valid: they contain one a or nine c, both within the limits of their respective policies.
 *
 * How many passwords are valid according to their policies?
 *
 */
class Day20201202 {

    data class Validation(val min: Int, val max: Int, val char: Char) {
        fun isValidFirst(input: String) : Boolean {
            val count = input.filter { it == char }.length
            return count in min..max
        }
        fun isValidSecond(input: String) : Boolean {
            return (input[min-1] == char) xor (input[max-1] == char)
        }
    }

    fun countValidPasswordsFirst(input : List<String>) : Int {
        val results: List<Int> = input
            .map {
                val match = Regex("(\\d+)-(\\d+) (\\w): (\\w+)").find(it)!!
                val (min, max, char, string) = match.destructured
                val validation = Validation(min.toInt(), max.toInt(), char[0])
                if (validation.isValidFirst(string)) {
                    1
                } else {
                    0
                }
            }
        return results.reduce { sum, element -> sum + element }
    }

    fun countValidPasswordsSecond(input : List<String>) : Int {
        val results: List<Int> = input
            .map {
                val match = Regex("(\\d+)-(\\d+) (\\w): (\\w+)").find(it)!!
                val (min, max, char, string) = match.destructured
                val validation = Validation(min.toInt(), max.toInt(), char[0])
                if (validation.isValidSecond(string)) {
                    1
                } else {
                    0
                }
            }
        return results.reduce { sum, element -> sum + element }
    }
}

fun main() {
    val inputRaw = Day20201202::class.java.classLoader.getResource("Day20201202.txt").readText()
    val input = inputRaw.split("\n")

    val firstOutput = Day20201202().countValidPasswordsFirst(input)
    println("Found $firstOutput valid passwords for first test")

    val secondOutput = Day20201202().countValidPasswordsSecond(input)
    println("Found $secondOutput valid passwords for second test")
}

