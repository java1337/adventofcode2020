package com.java1337.adventofcode2020

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day20201205Test {

    @Test
    fun `should work for first sample`() {

        // given
        val input = "BFFFBBFRRR"

        // when
        val boardingPass = Day20201205.BoardingPass(input)

        // then
        assertEquals(70, boardingPass.row)
        assertEquals(7, boardingPass.columnn)
        assertEquals(567, boardingPass.seatId)
    }

    @Test
    fun `should work for second sample`() {

        // given
        val input = "FFFBBBFRRR"

        // when
        val boardingPass = Day20201205.BoardingPass(input)

        // then
        assertEquals(14, boardingPass.row)
        assertEquals(7, boardingPass.columnn)
        assertEquals(119, boardingPass.seatId)
    }

    @Test
    fun `should work for third sample`() {

        // given
        val input = "BBFFBBFRLL"

        // when
        val boardingPass = Day20201205.BoardingPass(input)

        // then
        assertEquals(102, boardingPass.row)
        assertEquals(4, boardingPass.columnn)
        assertEquals(820, boardingPass.seatId)
    }
}