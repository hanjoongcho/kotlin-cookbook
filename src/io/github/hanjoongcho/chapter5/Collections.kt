package io.github.hanjoongcho.chapter5

val list1: MutableList<String> = mutableListOf<String>("apple", "banana", "pineapple")
val list2: MutableList<String> = mutableListOf<String>()

fun testIterable() {
    list2.addAll(list1)
    println(list2)
}