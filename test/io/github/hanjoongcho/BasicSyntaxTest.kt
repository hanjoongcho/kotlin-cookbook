package io.github.hanjoongcho.chapter1

import org.junit.Test
import org.junit.Assert.*


class BasicSyntaxTest {

    @Test fun testSum_01() {
        // 정수 파라미터 2개를 받아 정수값을 리턴하는 함수
        assertTrue(sum(3, 5) ==  8)
    }

    @Test fun testSum_02() {
        // 추론타입을 리턴하는 함수를 표현식과 함께사용
        assertEquals("sum of 19 and 23 is ${sum(19, 23)}", "sum of 19 and 23 is 42")
    }

    @Test fun testMaxOf_01() {
        assertTrue(maxOf(0, 42) == 42)
    }

    @Test fun testPrintSum() {
        // Unit타입(의미있는 값이 리턴하지 않음?? / kotlin.Unit)을 리턴하는 함수
        // Unit타입 선언은 생략가능함
        assertTrue(printSum(1, 10) is Unit)
    }

    @Test fun testGetLength() {
        // 타입확인과 자동 형변환
        assertTrue(getLength("Incomprehensibilities") == 21)
        assertTrue(getLength(1000) == -1)
        assertTrue(getLength(listOf(Any())) == -1)
    }

    @Test fun testStringTemplate() {
        // String Template
        assertEquals(simpleStringTemplate("Hello Kotlin"), "targetString is Hello Kotlin")
        assertEquals(expressionStringTemplate("A A B B C C", "A", "C"), "C C B B C C")
    }

    @Test fun testFitInRange() {
        // in 연산자를 사용하여 숫자가 범위 내에 있는지 확인
        assertFalse(fitInRange(5, 7, 4))
        assertTrue(fitInRange(5, 7, 5))
        assertTrue(fitInRange(5, 7, 6))
        assertTrue(fitInRange(5, 7, 7))
        assertFalse(fitInRange(5, 7, 8))
    }

    @Test fun testForLoop_01() {
        // for 반복문
        val items = listOf("apple", "banana", "kiwi")
        for (item in items) {
            assertTrue(item.matches("apple|banana|kiwi".toRegex()))
        }
    }

    @Test fun testForLoop_02() {
        // for 반복문
        val items = listOf("apple", "banana", "kiwi")
        for (index in items.indices) {
            when (index) {
                0 -> assertEquals(items[index], "apple")
                1 -> assertEquals(items[index], "banana")
                2 -> assertEquals(items[index], "kiwi")
            }
        }
    }

    @Test fun testWhileLoop() {
        val items = listOf("apple", "banana", "kiwi")
        var index = 0
        while (index < items.size) {
            println("item at $index is ${items[index]}")
            index++
        }
        println(index)
        assertTrue(index == 3)
    }


    @Test fun testRange_01() {
        val sb = StringBuilder()
        // IntRange 반복
        for (x in 1..5) {
            sb.append(x)
        }
        assertEquals(sb.toString(), "12345")
    }

    @Test fun testRange_02() {
        val sb = StringBuilder()
        for (x in 1..10 step 2) {
            sb.append(x)
        }
        assertEquals(sb.toString(), "13579")
    }

    @Test fun testRange_03() {
        val sb = StringBuilder()
        for (x in 9 downTo 0 step 3) {
            sb.append(x)
        }
        assertEquals(sb.toString(), "9630")
    }

    @Test fun testWhenExpression() {
        assertEquals(describe(1), "One")
        assertEquals(describe("Hello"), "Greeting")
        assertEquals(describe(1000L), "Long")
        assertEquals(describe("other"), "Unknown")
    }

    @Test fun tesInOperator() {
        val items = setOf("apple", "banana", "kiwi")
        assertTrue("apple" in items)
        assertTrue("banana" in items)
        assertFalse("pineapple" in items)
    }

    @Test fun testFilter() {
        val fruits = listOf("banana", "avocado", "apple", "kiwi")
        val list = fruits.filter { item -> item == "apple" }
        assertEquals(list.first(), "apple")
    }

    @Test fun testSortBy() {
        val fruits = listOf("banana", "avocado", "apple", "kiwi")
        val list = fruits.sortedBy { it }
        assertEquals(list.first(), "apple")
    }

    @Test fun testMap() {
        val fruits = listOf("banana", "avocado", "apple", "kiwi")
        assertEquals(fruits.map { it.toUpperCase() }.first(), "BANANA")
    }

}