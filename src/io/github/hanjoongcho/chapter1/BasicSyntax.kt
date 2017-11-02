package io.github.hanjoongcho.chapter1

fun main(args: Array<String>) {

    // 한번만 할당가능한(읽기전용) 지역변수 정의
    defineLocalVariable()

    // 여러번 할당가능한 변수 정의
    defineLocalVariable2()

    // 한줄 주석
    // This is an end-of-line comment

    // 블럭 주석(Java와 달리 Kotlin의 블록 주석은 중첩 될 수 있습니다.)
    /* This is a block comment
       on multiple lines. */

    // null값 할당이 가능한 변수와 null값 체크
    printProduct("6", "7")
    printProduct("a", "7")
    printProduct("a", "b")

    // null값 할당이 가능한 변수와 null값 체크 2
    printProduct2("6", "7")
    printProduct2("a", "7")
    printProduct2("99", "b")

    val list = listOf("a", "b", "c")
    if (-1 !in 0..list.lastIndex) {
        println("-1 is out of range")
    }
    if (list.size !in list.indices) {
        println("list size is out of valid list indices range too")
    }

    // in 연산자를 사용하여 컬렉션에 개체가 포함되어 있는지 확인
    collections2();

}

fun fitInRange(start: Int, end: Int, targetNum: Int): Boolean {
    return targetNum in start..end
}

fun sum(a: Int, b: Int): Int {
    return a + b
}

fun printSum(a: Int, b: Int): Unit {
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

fun simpleStringTemplate(targetString: String): String = "targetString is $targetString"

fun expressionStringTemplate(origin: String, target: String, replacement: String): String = "${origin.replace(target, replacement)}"

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

fun getLength(obj: Any): Int = getStringLength(obj) ?: -1

fun describe(obj: Any): String =
        when (obj) {
            1          -> "One"
            "Hello"    -> "Greeting"
            is Long    -> "Long"
            !is String -> "Not a string"
            else       -> "Unknown"
        }

fun collections2() {
    val items = setOf("apple", "banana", "kiwi")
    when {
        "orange" in items -> println("juicy")
        "apple" in items -> println("apple is fine too")
    }
}