package io.github.hanjoongcho.chapter1

fun main(args: Array<String>) {

    // 정수 파라미터 2개를 받아 정수값을 리턴하는 함수
    print("sum of 3 and 5 is ")
    println(sum(3, 5))

    // 추론타입을 리턴하는 함수를 표현식과 함께사용
    println("sum of 19 and 23 is ${sum(19, 23)}")

    // Unit타입(의미있는 값이 리턴하지 않음?? / kotlin.Unit)을 리턴하는 함수
    printSum(-1, 8)

    // Unit타입 선언을 생략
    printSum(-1, 8)

    // 한번만 할당가능한(읽기전용) 지역변수 정의
    defineLocalVariable();

    // 여러번 할당가능한 변수 정의
    defineLocalVariable2();

    // 한줄 주석
    // This is an end-of-line comment

    // 블럭 주석(Java와 달리 Kotlin의 블록 주석은 중첩 될 수 있습니다.)
    /* This is a block comment
       on multiple lines. */

    // string 템플릿
    stringTemplate();

    // 조건문
    println("max of 0 and 42 is ${maxOf(0, 42)}")

    // null값 할당이 가능한 변수와 null값 체크
    printProduct("6", "7")
    printProduct("a", "7")
    printProduct("a", "b")

    // null값 할당이 가능한 변수와 null값 체크 2
    printProduct2("6", "7")
    printProduct2("a", "7")
    printProduct2("99", "b")

    // 타입확인과 자동 형변환
    printLength("Incomprehensibilities")
    printLength(1000)
    printLength(listOf(Any()))

    // for 반복문
    val items = listOf("apple", "banana", "kiwi")
    for (item in items) {
        println(item)
    }

    for (index in items.indices) {
        println("item at $index is ${items[index]}")
    }

    var index = 0
    while (index < items.size) {
        println("item at $index is ${items[index]}")
        index++
    }

    // when 표현식
    println(describe(1))
    println(describe("Hello"))
    println(describe(1000L))
    println(describe(2))
    println(describe("other"))

    // in 연산자를 사용하여 숫자가 범위 내에 있는지 확인
    val x = 10
    val y = 9
    if (x in 1..y+1) {
        println("fits in range")
    }

    val list = listOf("a", "b", "c")
    if (-1 !in 0..list.lastIndex) {
        println("-1 is out of range")
    }
    if (list.size !in list.indices) {
        println("list size is out of valid list indices range too")
    }

    // IntRange 반복
    for (x in 1..5) {
        print(x)
    }

    // or over a progression
    for (x in 1..10 step 2) {
        print(x)
    }
    for (x in 9 downTo 0 step 3) {
        print(x)
    }

    // 컬렉션 반복
    collections();

    // in 연산자를 사용하여 컬렉션에 개체가 포함되어 있는지 확인
    collections2();

    // 람다 식을 사용하여 콜렉션 필터링 및 매핑
    val fruits = listOf("banana", "avocado", "apple", "kiwi")
    fruits
        .filter { it.startsWith("a") }
        .sortedBy { it }
        .map { it.toUpperCase() }
        .forEach { println(it) }
    for (fruit in fruits) {
        println(fruit)
    }
}

fun sum(a: Int, b: Int): Int {
    return a + b
}

fun sum2(a: Int, b: Int) = a + b


fun printSum(a: Int, b: Int): Unit {
    println("sum of $a and $b is ${a + b}")
}

fun printSum2(a: Int, b: Int) {
    println("sum of $a and $b is ${a + b}")
}

fun defineLocalVariable() {
    val a: Int = 1  // immediate assignment
    val b = 2   // `Int` type is inferred
    val c: Int  // Type required when no initializer is provided
    c = 3       // deferred assignment
}

fun defineLocalVariable2() {
    var x = 5 // `Int` type is inferred
    x += 1
    println("x = $x")
}

fun stringTemplate() {
    var a = 1
    // simple name in template:
    val s1 = "a is $a"

    a = 2
    // arbitrary expression in template:
    val s2 = "${s1.replace("is", "was")}, but now is $a"
    println(s2)
}

fun maxOf(a: Int, b: Int): Int {
    if (a > b) {
        return a
    } else {
        return b
    }
}

fun parseInt(str: String): Int? {
    return str.toIntOrNull()
}

fun printProduct(arg1: String, arg2: String) {
    val x = parseInt(arg1)
    val y = parseInt(arg2)

    // Using `x * y` yields error because they may hold nulls.
    if (x != null && y != null) {
        // x and y are automatically cast to non-nullable after null check
        println(x * y)
    }
    else {
        println("either '$arg1' or '$arg2' is not a number")
    }
}

fun printProduct2(arg1: String, arg2: String) {
    val x = parseInt(arg1)
    val y = parseInt(arg2)

    // ...
    if (x == null) {
        println("Wrong number format in arg1: '${arg1}'")
        return
    }
    if (y == null) {
        println("Wrong number format in arg2: '${arg2}'")
        return
    }

    // x and y are automatically cast to non-nullable after null check
    println(x * y)
}

fun getStringLength(obj: Any): Int? {
    if (obj is String) {
        // `obj` is automatically cast to `String` in this branch
        return obj.length
    }

    // `obj` is still of type `Any` outside of the type-checked branch
    return null
}

fun printLength(obj: Any) {
    println("'$obj' string length is ${getStringLength(obj) ?: "... err, not a string"} ")
}

fun describe(obj: Any): String =
        when (obj) {
            1          -> "One"
            "Hello"    -> "Greeting"
            is Long    -> "Long"
            !is String -> "Not a string"
            else       -> "Unknown"
        }


fun collections() {
    val items = listOf("apple", "banana", "kiwi")
    for (item in items) {
        println(item)
    }
}

fun collections2() {
    val items = setOf("apple", "banana", "kiwi")
    when {
        "orange" in items -> println("juicy")
        "apple" in items -> println("apple is fine too")
    }
}