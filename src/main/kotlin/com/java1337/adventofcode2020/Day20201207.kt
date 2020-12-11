package com.java1337.adventofcode2020

/**
 * You land at the regional airport in time for your next flight. In fact,
 * it looks like you'll even have time to grab some food: all flights are
 * currently delayed due to issues in luggage processing.
 *
 * Due to recent aviation regulations, many rules (your puzzle input) are being
 * enforced about bags and their contents; bags must be color-coded and must
 * contain specific quantities of other color-coded bags. Apparently, nobody
 * responsible for these regulations considered how long they would take to
 * enforce!
 *
 * For example, consider the following rules:
 *
 * light red bags contain 1 bright white bag, 2 muted yellow bags.
 * dark orange bags contain 3 bright white bags, 4 muted yellow bags.
 * bright white bags contain 1 shiny gold bag.
 * muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
 * shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
 * dark olive bags contain 3 faded blue bags, 4 dotted black bags.
 * vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
 * faded blue bags contain no other bags.
 * dotted black bags contain no other bags.
 * These rules specify the required contents for 9 bag types. In this example,
 * every faded blue bag is empty, every vibrant plum bag contains 11 bags (5
 * faded blue and 6 dotted black), and so on.
 *
 * You have a shiny gold bag. If you wanted to carry it in at least one other
 * bag, how many different bag colors would be valid for the outermost bag?
 * (In other words: how many colors can, eventually, contain at least one
 * shiny gold bag?)
 *
 * In the above rules, the following options would be available to you:
 *
 * A bright white bag, which can hold your shiny gold bag directly.
 * A muted yellow bag, which can hold your shiny gold bag directly, plus
 * some other bags.
 * A dark orange bag, which can hold bright white and muted yellow bags,
 * either of which could then hold your shiny gold bag.
 * A light red bag, which can hold bright white and muted yellow bags,
 * either of which could then hold your shiny gold bag.
 * So, in this example, the number of bag colors that can eventually contain
 * at least one shiny gold bag is 4.
 *
 * How many bag colors can eventually contain at least one shiny gold bag?
 * (The list of rules is quite long; make sure you get all of it.)
 *
 */
class Day20201207(input: List<String>) {

    private val graph = DirectedGraph<Vertex>()

    init {

        // "light red bags contain 1 bright white bag, 2 muted yellow bags."
        val beforeMatcher = Regex("(\\w+) (\\w+) bags")
        val afterMatcher = Regex("(\\d) (\\w+) (\\w+) bag")

        input.forEach {
            val splitter = it.split(" contain ")
            val beforeContain = splitter[0]
            val afterContain = splitter[1].replace("bags", "bag")
            val (beforeModifier, beforeColor) = beforeMatcher.find(beforeContain)!!.destructured
            val beforeVertex = Vertex(beforeColor, beforeModifier)
            if ("no other bag." != afterContain) {
                afterContain.split(",").map { afterFragment ->
                    val (afterCount, afterModifier, afterColor) = afterMatcher.find(afterFragment)!!.destructured
                    val afterVertex = Vertex(afterColor, afterModifier)
                    println("$beforeVertex -> $afterCount * $afterVertex")
                    graph.addEdge(afterVertex, beforeVertex, afterCount.toInt())
                }
            } else {
                println("$beforeVertex -> nothing")
            }
        }
        println(graph)
    }

    data class Vertex(val color: String, val modifier: String)


    class DirectedGraph<T> {

        data class Edge<T>(val source : T, val destination: T)
        val weightMap = HashMap<Edge<T>, Int>()
        val adjacencyMap: HashMap<T, HashSet<T>> = HashMap()
        val reverseMap: HashMap<T, HashSet<T>> = HashMap()

        fun addEdge(sourceVertex: T, destinationVertex: T, weight: Int) {
            // Add edge to source vertex / node.
            adjacencyMap
                .computeIfAbsent(sourceVertex) { HashSet() }
                .add(destinationVertex)

            reverseMap
                .computeIfAbsent(destinationVertex) { HashSet() }
                .add(sourceVertex)

            weightMap[Edge(destinationVertex, sourceVertex)] = weight
        }


        fun getDescendents(vertex: T, maxDepth: Int) : Set<T> {
            if (maxDepth == 0 || !adjacencyMap.containsKey(vertex)) {
                return emptySet()
            }
            val children = adjacencyMap[vertex] ?: emptySet()
            children.forEach { println ("*** X $maxDepth - found $it") }

            val grandchildren = children.flatMap { getDescendents(it, maxDepth - 1).toList() }.toSet()

            val total = children union grandchildren

            println("total size so far for $vertex: ${total.size} (children: ${children.size}, grandchildren: ${grandchildren.size}")
            return total
        }

        fun getDescendentWeights(vertex: T, maxDepth: Int) : Int {
            if (maxDepth == 0 || !reverseMap.containsKey(vertex)) {
                return 1
            }
            val children: Set<T> = reverseMap[vertex] ?: emptySet()

            val grandchildren = children.map {
                val edge = Edge(vertex, it)
                val weight = weightMap[edge]
                val descendents = getDescendentWeights(it, maxDepth - 1)
                println ("*** X $maxDepth ($vertex) - found $it (weight $weight) x ($descendents descendents)")
                getDescendentWeights(it, maxDepth - 1) * (weight?: 1)
            }.sum()

            val total = grandchildren + 1

            println("total size so far for $vertex: $total (children: ${children.size}, grandchildren: $grandchildren)")
            return total
        }

        override fun toString(): String = StringBuffer().apply {
            for (key in adjacencyMap.keys) {
                append("$key -> ")
                append(adjacencyMap[key]?.joinToString(", ", "[", "]\n"))
            }
        }.toString()
    }

    fun countBags(): Int {
        return graph.getDescendents(Vertex("gold", "shiny"), 10).size
    }

    fun countTotalBags(): Int {
        return graph.getDescendentWeights(Vertex("gold", "shiny"), 10) - 1
    }

}

fun main() {
    val input = Utils.readFileAsListOfString("Day20201207.txt")
    val obj = Day20201207(input)
    println("There are ${obj.countBags()} ways to get shiny gold bags")
    println("There are ${obj.countTotalBags()} total bags needed")
}

