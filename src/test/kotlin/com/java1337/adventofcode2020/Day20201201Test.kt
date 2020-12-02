package com.java1337.adventofcode2020

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day20201201Test {

    @Test
    fun `return null if there are no sums to 2020`() {

        // given
        val input = listOf(1, 2, 3)

        // when
        val actual = Day20201201().productForTwoInputsThatSomeTo2020(input)

        // thne
        assertEquals(null, actual)
    }

    @Test
    fun `return product if there is one sum to 2020`() {

        // given
        val input = listOf(1, 2019)

        // when
        val actual = Day20201201().productForTwoInputsThatSomeTo2020(input)

        // thne
        assertEquals(2019, actual)
    }

    @Test
    fun `return null if 1010 is in the list once`() {

        // given
        val input = listOf(1,1010)

        // when
        val actual = Day20201201().productForTwoInputsThatSomeTo2020(input)

        // thne
        assertEquals(null, actual)
    }

    @Test
    fun `return product if 1010 is in the more than once`() {

        // given
        val input = listOf(1,1010,1010)

        // when
        val actual = Day20201201().productForTwoInputsThatSomeTo2020(input)

        // thne
        assertEquals(1020100, actual)
    }

    @Test
    fun `return right value for the sample input`() {

        // given
        val input = listOf(1721, 979, 366, 299, 675, 1456)

        // when
        val actual = Day20201201().productForTwoInputsThatSomeTo2020(input)

        // thne
        assertEquals(514579, actual)
    }
}