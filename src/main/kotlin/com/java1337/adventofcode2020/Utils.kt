package com.java1337.adventofcode2020

object Utils {

    fun readFileAsListOfString(file : String) : List<String> {
        @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        val inputRaw = Utils::class.java.classLoader.getResource(file).readText()
        val input = inputRaw.split("\n")
        return input
    }
}