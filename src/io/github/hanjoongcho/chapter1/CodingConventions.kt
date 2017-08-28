package io.github.hanjoongcho.chapter1

/**
 * # Naming Style
 *
 * ## If in doubt, default to the Java Coding Conventions such as:
 *  - use of camelCase for names (and avoid underscore in names)
 *  - types start with upper case
 *  - methods and properties start with lower case
 *  - use 4 space indentation
 *  - public functions should have documentation such that it appears in Kotlin Doc
 *
 * [coding-conventions.html](https://kotlinlang.org/docs/reference/coding-conventions.html)
 *
 */
class SuperMarket() {

    /**
     * displayBanana function description
     */
    fun displayBanana() {
        println("display banana")
    }
}

fun main(args: Array<String>) {

    var superMarket = SuperMarket()
    superMarket.displayBanana()
}