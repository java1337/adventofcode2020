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
 */
class Day20201201 {

    fun productForTwoInputsThatSomeTo2020(input: List<Int>): Int? {
        if (input.size < 2)
            return null

        val filteredInput = filter1010IfThereIsOnlyOne(input)
        val sortedInput = filteredInput.sorted()

        sortedInput.forEach {
            val opposite = 2020 - it
            if (sortedInput.contains(opposite)) {
                return it * opposite
            }
        }

        return null
    }

    private fun filter1010IfThereIsOnlyOne(input: List<Int>) : List<Int> {
        return if (input.filter { it == 1010 }.size == 1) {
            input.filterNot { it == 1010 }
        } else {
            input
        }
    }
}


fun main() {
    val input = listOf(
        1788,
        1627,
        1883,
        1828,
        1924,
        1993,
        972,
        1840,
        1866,
        1762,
        1781,
        1782,
        1520,
        1971,
        1660,
        1857,
        1867,
        1564,
        1983,
        1391,
        2002,
        494,
        1500,
        1967,
        1702,
        1958,
        1886,
        1910,
        1838,
        1985,
        1836,
        2009,
        2005,
        1602,
        1939,
        1945,
        1609,
        1582,
        1647,
        1737,
        1982,
        1931,
        790,
        745,
        1598,
        1586,
        1547,
        1951,
        1264,
        1382,
        1776,
        1499,
        1977,
        1766,
        1360,
        1807,
        1991,
        1981,
        1693,
        634,
        1847,
        1774,
        1990,
        1409,
        1410,
        1974,
        1862,
        1744,
        1827,
        1978,
        1980,
        2003,
        1491,
        1595,
        1640,
        1576,
        1887,
        1746,
        1617,
        1923,
        1706,
        1964,
        60,
        1620,
        1959,
        257,
        1395,
        1854,
        1843,
        1682,
        1667,
        1639,
        279,
        1911,
        1986,
        1575,
        1232,
        1919,
        1852,
        1509,
        1976,
        1465,
        2008,
        1953,
        1518,
        1795,
        1912,
        1269,
        1835,
        1984,
        1538,
        2001,
        1954,
        1365,
        1569,
        1418,
        1844,
        1580,
        1875,
        1551,
        1861,
        1946,
        1810,
        1655,
        1987,
        1549,
        1301,
        1859,
        1929,
        1254,
        1604,
        1933,
        1998,
        1661,
        1899,
        1411,
        1975,
        1707,
        1966,
        1601,
        1936,
        1440,
        1942,
        1937,
        1851,
        1731,
        1257,
        1533,
        1405,
        1890,
        1600,
        1970,
        1626,
        1824,
        1442,
        2006,
        1796,
        1658,
        1930,
        646,
        1904,
        1489,
        2004,
        1922,
        1424,
        1802,
        1623,
        1870,
        1242,
        1591,
        1338,
        754,
        1826,
        1305,
        1825,
        1793,
        1872,
        1741,
        1979,
        107,
        1833,
        1856,
        1952,
        1791,
        1728,
        2010,
        1965,
        1646,
        1522,
        1671,
        1624,
        1876,
        1537,
        1759,
        1962,
        1773,
        1907,
        1573,
        1908,
        1903)

    println("input.size = ${input.size} with input[0] = '${input[0]}' and input[1] = '${input[1]}'")

    when (val maybeResult: Int? = Day20201201().productForTwoInputsThatSomeTo2020(input)) {
        null -> println("There's no result for this input")
        else -> println("Found a product of $maybeResult")
    }
}