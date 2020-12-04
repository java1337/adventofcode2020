package com.java1337.adventofcode2020

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day20201203Test {

    @Test
    fun `shouldCountTreesHitWithSampleInputFirst`() {

        // given
        val input = listOf(
            "..##.......",
            "#...#...#..",
            ".#....#..#.",
            "..#.#...#.#",
            ".#...##..#.",
            "..#.##.....",
            ".#.#.#....#",
            ".#........#",
            "#.##...#...",
            "#...##....#",
            ".#..#...#.#"
        )

        // when
        val actual = Day20201203().countTreesHit(input, 3)

        // then
        assertEquals(7, actual)
    }

    @Test
    fun `shouldCountTreesHitWithSampleInputSecond`() {

        // given
        val input = listOf(
            "..##.......",
            "#...#...#..",
            ".#....#..#.",
            "..#.#...#.#",
            ".#...##..#.",
            "..#.##.....",
            ".#.#.#....#",
            ".#........#",
            "#.##...#...",
            "#...##....#",
            ".#..#...#.#"
        )

        // when
        val actual = Day20201203().countTreesHitAllSlope(input)

        // then
        assertEquals(336, actual)
    }
}