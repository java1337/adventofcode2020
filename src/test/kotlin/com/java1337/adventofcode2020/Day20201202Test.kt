package com.java1337.adventofcode2020

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day20201202Test {

    @Test
    fun shouldCountValidPasswordsWithSampleInputFirst() {

        // given
        val input = listOf(
            "1-3 a: abcde",
            "1-3 b: cdefg",
            "2-9 c: ccccccccc"
        )

        // when
        val actual = Day20201202(input).countValidPasswordsFirst()

        // then
        assertEquals(2, actual)
    }

    @Test
    fun shouldCountValidPasswordsWithSampleInputSecond() {

        // given
        val input = listOf(
            "1-3 a: abcde",
            "1-3 b: cdefg",
            "2-9 c: ccccccccc"
        )

        // when
        val actual = Day20201202(input).countValidPasswordsSecond()

        // then
        assertEquals(1, actual)
    }
}
