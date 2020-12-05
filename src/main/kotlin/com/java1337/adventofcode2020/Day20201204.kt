package com.java1337.adventofcode2020

/**
 *
 * You arrive at the airport only to realize that you grabbed your North
 * Pole Credentials instead of your passport. While these documents are
 * extremely similar, North Pole Credentials aren't issued by a country
 * and therefore aren't actually valid documentation for travel in most
 * of the world.
 *
 * It seems like you're not the only one having problems, though; a very
 * long line has formed for the automatic passport scanners, and the delay
 * could upset your travel itinerary.
 *
 * Due to some questionable network security, you realize you might be able
 * to solve both of these problems at the same time.
 *
 * The automatic passport scanners are slow because they're having trouble
 * detecting which passports have all required fields. The expected fields
 * are as follows:
 *
 * byr (Birth Year)
 * iyr (Issue Year)
 * eyr (Expiration Year)
 * hgt (Height)
 * hcl (Hair Color)
 * ecl (Eye Color)
 * pid (Passport ID)
 * cid (Country ID)
 *
 * Passport data is validated in batch files (your puzzle input). Each
 * passport is represented as a sequence of key:value pairs separated by
 * spaces or newlines. Passports are separated by blank lines.
 *
 * Here is an example batch file containing four passports:
 *
 * ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
 * byr:1937 iyr:2017 cid:147 hgt:183cm
 *
 * iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
 * hcl:#cfa07d byr:1929
 *
 * hcl:#ae17e1 iyr:2013
 * eyr:2024
 * ecl:brn pid:760753108 byr:1931
 * hgt:179cm
 *
 * hcl:#cfa07d eyr:2025 pid:166559648
 * iyr:2011 ecl:brn hgt:59in
 * The first passport is valid - all eight fields are present. The second
 * passport is invalid - it is missing hgt (the Height field).
 *
 * The third passport is interesting; the only missing field is cid, so it
 * looks like data from North Pole Credentials, not a passport at all!
 * Surely, nobody would mind if you made the system temporarily ignore
 * missing cid fields. Treat this "passport" as valid.
 *
 * The fourth passport is missing two fields, cid and byr. Missing cid
 * is fine, but missing any other field is not, so this passport is invalid.
 *
 * According to the above rules, your improved system would report 2 valid
 * passports.
 *
 * Count the number of valid passports - those that have all required fields.
 * Treat cid as optional. In your batch file, how many passports are valid?
 *
 */
class Day20201204(input: List<String>) {

    val passports : List<Passport>

    init {
        val passports = mutableListOf<Passport>()
        var currentPassport = Passport()
        passports.add(currentPassport)
        input.forEach {
            if (it == "") {
                currentPassport = Passport()
                passports.add(currentPassport)
            } else {
                it.split(" ").map {
                    val keyAndValue = it.split(":")
                    currentPassport.addAttribute(keyAndValue.first(), keyAndValue.last())
                }
            }
        }
        this.passports = passports
    }

    class Passport {
        var byr : String? = null
        var iyr : String? = null
        var eyr : String? = null
        var hgt : String? = null
        var hcl : String? = null
        var ecl : String? = null
        var pid : String? = null

        fun addAttribute(key: String, value: String) {
            when (key) {
                "byr" -> byr = value
                "iyr" -> iyr = value
                "eyr" -> eyr = value
                "hgt" -> hgt = value
                "hcl" -> hcl = value
                "ecl" -> ecl = value
                "pid" -> pid = value
            }
        }

        fun hasRequiredValue() : Boolean {
            return !byr.isNullOrEmpty() && !iyr.isNullOrEmpty() && !eyr.isNullOrEmpty() && !hgt.isNullOrEmpty() && !hcl.isNullOrEmpty() && !ecl.isNullOrEmpty() && !pid.isNullOrEmpty()
        }

        fun hasValidValues() : Boolean {
            val returnValue = hasValidValues2()
            println("$returnValue : $byr | $iyr | $eyr | $hgt | $hcl | $ecl | $pid")
            return returnValue
        }

        fun hasValidValues2() : Boolean {
            if (!hasRequiredValue()) {
                return false
            }
            try {
                // byr (Birth Year) - four digits; at least 1920 and at most 2002.
                if (byr == null || byr!!.toInt() !in 1920..2002)
                    return false

                // iyr (Issue Year) - four digits; at least 2010 and at most 2020.
                if (iyr == null || iyr!!.toInt() !in 2010..2020)
                    return false

                // eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
                if (eyr == null || eyr!!.toInt() !in 2020..2030)
                    return false

                // hgt (Height) - a number followed by either cm or in:
                // If cm, the number must be at least 150 and at most 193.
                // If in, the number must be at least 59 and at most 76.
                val (value, unit) = Regex("(\\d+)(\\w+)").find(hgt!!)!!.destructured
                when (unit) {
                    "cm" -> if (value.toInt() !in 150..193) return false
                    "in" -> if (value.toInt() !in 59..76) return false
                    else -> return false
                }

                // hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
                if (!Regex("#[0-9a-f]{6}").matches(hcl!!))
                    return false

                // ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
                if (!Regex("(amb|blu|brn|gry|grn|hzl|oth)").matches(ecl!!))
                    return false

                // pid (Passport ID) - a nine-digit number, including leading zeroes.
                if (!Regex("[0-9]{9}").matches(pid!!))
                    return false


            } catch (e: Exception) {
                return false
            }
            return true
        }
    }


    fun countPassportsWithRequiredFields(): Int {
        return this.passports.count(Passport::hasRequiredValue)
    }

    fun countValidPassports(): Int {
        return this.passports.filter(Passport::hasValidValues).size
    }

}

fun main() {
    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    val inputRaw = Day20201203::class.java.classLoader.getResource("Day20201204.txt").readText()
    val input = inputRaw.split("\n")

    val obj = Day20201204(input)
    println("Passports with required fields = ${obj.countPassportsWithRequiredFields()}")
    println("Valid Passports = ${obj.countValidPassports()}")
}

