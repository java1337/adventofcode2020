package com.java1337.adventofcode2020

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day20201207Test  {

    @Test
    fun shouldGetCountForSampleInputPartOne() {

        // given
        val obj = Day20201207(getSampleInputFirst())

        // when
        val actual = obj.countBags()

        // then
        assertEquals(4, actual)
    }

    @Test
    fun shouldGetCountForSampleInputPartTwoA() {

        // given
        val obj = Day20201207(getSampleInputSecond())

        // when
        val actual = obj.countTotalBags()

        // then
        assertEquals(126, actual)
    }

    @Test
    fun shouldGetCountForSampleInputPartTwoB() {

        // given
        val obj = Day20201207(getSampleInputFirst())

        // when
        val actual = obj.countTotalBags()

        // then
        assertEquals(32, actual)
    }


    private fun getSampleInputFirst() : List<String> {
        return listOf(
            "light red bags contain 1 bright white bag, 2 muted yellow bags.",
            "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
            "bright white bags contain 1 shiny gold bag.",
            "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
            "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
            "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
            "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
            "faded blue bags contain no other bags.",
            "dotted black bags contain no other bags."
        )
    }
    private fun getSampleInputSecond() : List<String> {
        return listOf(
            "shiny gold bags contain 2 dark red bags.",
            "dark red bags contain 2 dark orange bags.",
            "dark orange bags contain 2 dark yellow bags.",
            "dark yellow bags contain 2 dark green bags.",
            "dark green bags contain 2 dark blue bags.",
            "dark blue bags contain 2 dark violet bags.",
            "dark violet bags contain no other bags."
        )
    }
}