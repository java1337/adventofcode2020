package com.java1337.adventofcode2020

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day20201207Test  {

    @Test
    fun shouldGetCountForSampleInputPartOne() {

        // given
        val obj = Day20201207(getSampleInput())

        // when
        val actual = obj.countBags()

        // then
        assertEquals(4, actual)
    }

    private fun getSampleInput() : List<String> {
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
}