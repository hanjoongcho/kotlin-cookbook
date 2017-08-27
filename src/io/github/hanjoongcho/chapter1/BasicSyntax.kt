package io.github.hanjoongcho.chapter1

fun main(args: Array<String>) {

    // 정수 파라미터 2개를 받아 정수값을 리턴하는 함수
    print("sum of 3 and 5 is ")
    println(sum(3, 5))

    // 추론타입을 리턴하는 함수를 표현식과 함께사용
    println("sum of 19 and 23 is ${sum(19, 23)}")
}

fun sum(a: Int, b: Int): Int {
    return a + b
}

fun sum2(a: Int, b: Int) = a + b