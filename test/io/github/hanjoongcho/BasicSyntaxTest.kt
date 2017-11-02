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

}