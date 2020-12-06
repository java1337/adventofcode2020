package com.java1337.adventofcode2020

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day20201206Test {

    @Test
    fun shouldCountUnionTotals() {

        // given
        val obj = Day20201206(getSampleInput())

        // when
        val actual = obj.getPassengerUnionGroupSumCount()

        // then
        assertEquals(11, actual)
    }

    @Test
    fun shouldCountIntersectionTotals() {

        // given
        val obj = Day20201206(getSampleInput())

        // when
        val actual = obj.getPassengerIntersectionGroupSumCount()

        // then
        assertEquals(6, actual)
    }

    private fun getSampleInput() : List<String> {
        return listOf(
            "abc",
            "",
            "a",
            "b",
            "c",
            "",
            "ab",
            "ac",
            "",
            "a",
            "a",
            "a",
            "a",
            "",
            "b"
        )
    }
}