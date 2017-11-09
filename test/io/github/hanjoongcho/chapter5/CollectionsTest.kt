package io.github.hanjoongcho.chapter5

import org.junit.Assert.*
import org.junit.Test

class CollectionsTest {

    val list1: MutableList<String> = mutableListOf<String>("apple", "banana", "pineapple")
    val list2: MutableList<String> = mutableListOf<String>()

    @Test fun test_01() {
        list2.addAll(list1)
        assertEquals(list1, list2)
    }

}