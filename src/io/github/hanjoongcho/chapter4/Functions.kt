package io.github.hanjoongcho.chapter4

import java.util.*

fun main(args: Array<String>) {
    defaultArguments(arrayOf(1, 2, 100), 1)
    foo(){ println("hello") }

//    val a = arrayOf(1, 2, 3)
//    val list = Arrays.asList(*a)
//    for (index in list.indices) {
//        println(list[index])
//    }
}

/**
 * Function parameters can have default values, which are used when a corresponding argument is omitted.
 * This allows for a reduced number of overloads compared to other languages
 */
fun defaultArguments(b: Array<Byte>, off: Int = 0, len: Int = b.size) {
    println(len)
}

fun foo(bar: Int = 0, baz: Int = 1, qux: () -> Unit) {
    qux()
    println(bar)
    println(baz)
}