package io.github.hanjoongcho.chapter2

import java.lang.Integer.parseInt

fun main(args: Array<String>) {
    // If Expression
    // Kotlin에서 if는 표현식입니다. 즉, 값을 반환합니다.
    // 그러므로 삼항 연산자 (condition? then : else)가 없습니다. 왜냐하면 보통 if가 이 역할을 잘 수행하기 때문입니다.
    println(ifExpression(5, 7))

    // 분기는 블록이 될 수 있고 마지막 표현식의 값이 리턴됩니다.
    // 명령문이 아닌 표현식으로 if를 사용한다면 반드시 else 분기가 정의되어야 합니다.
    println(ifExpression2(5, 7))

    // When Expression
    // when 표현식은 C와 유사한 언어의 switch 연산자를 대체합니다.
    // when은 분기 조건이 충족 될 때까지 모든 분기에 대한 인수를 순차적으로 찾습니다.
    // when은 표현식이나 명령문으로 사용될 수 있습니다.
    // 표현식으로 사용되면 만족 된 분기의 값이 전체 표현식의 값이됩니다.
    // 명령문으로 사용되면 개별 분기의 값은 무시됩니다. (if와 마찬가지로 각 분기는 블록이 될 수 있고 마지막 표현식의 값이 리턴됩니다.)
    // 분기 조건이 충족되지 않으면 else 블럭이 실행됩니다.
    // 표현식으로 when이 사용될 경우 컴파일러가 모든 가능한 경우의 분기처리를 보장하지 못한다면 else 분기가 필수로 정의되어야 합니다.
    whenExpression(1)
    whenExpression(3)

    // 많은 경우가 동일한 방식으로 처리되어야 하는 경우 콤마를 이용하여 2개이상의 조건을 분기의 조건으로 사용할 수 있습니다.
    whenExpression2(1)
    whenExpression2(3)

    // 분기 조건으로 임의의 표현식(상수뿐만 아니라)을 사용 할 수 있습니다.
    whenExpression3(2)
    whenExpression3(3)

    // 분기 조건으로 범위 또는 컬렉션에있는 값을 사용 할 수 있습니다.
    whenExpression4(3)
    whenExpression4(33)

    // 는 방법도 있습니다.
    // 스마트 캐스트 (smart cast)를 이용하면 값이 특정 데이터 타입인지 확인하고, 추가 검사없이 메서드 및 속성에 액세스 할 수 있습니다.
    println(hasPrefix(6000))
    println(hasPrefix("prefix_test_book"))

    // when can also be used as a replacement for an if-else if chain. If no argument is supplied, the branch conditions are simply boolean expressions, and a branch is executed when its condition is true:
    // when 표현식은 if-else 제어문을 대체 할 수 있습니다.
    // argument 값이 없는경우 분기조건에 boolean 표현식을 사용 할 수 있으며, 표현식의 결과가 true인 경우 분기문이 실행됩니다.
    var SuperMarket = SuperMarket()
    when {
        SuperMarket.isOdd() -> println("x is odd")
        SuperMarket.isEven() -> println("x is even")
        else -> println("x is funny")
    }

    // For Loops
    // for loop iterates through anything that provides an iterator. This is equivalent to the foreach loop in languages like C#. The syntax is as follows:
    // iterator만 제공되면 for 루프를 이용하여 순회가 가능합니다.
    // 이것은 C #과 같은 언어의 foreach 루프와 동일합니다.
    var fruits = listOf("감", "수박", "자두")
    forLoop(fruits)
    forLoop2(fruits)

    // 인덱스가있는 배열이나 목록을 반복 할 경우 다음과 같이 할 수 있습니다.
    forLoop3(fruits)

    // Alternatively, you can use the withIndex library function
    // withIndex 라이브러리 함수를 이용할수도 있습니다.
    forLoop4(fruits)

    // While Loops
    // while, do..while 루프는 기존의 방식과 동일합니다.
    
}

class SuperMarket() {
    fun isOdd(): Boolean {
        var result = false
        return result
    }

    fun isEven(): Boolean {
        var result = false
        return result
    }
}

fun ifExpression(a: Int, b: Int): Int {
    // Traditional usage with else
//    var max = a
//    if (a > b) {
//        max = a
//    } else {
//        max = b
//    }

    // As expression
    var max = if (a > b) a else b
    return max
}

fun ifExpression2(a: Int, b: Int): Int = if (a > b) {
    println("Choose a")
    a
} else {
    println("Choose b")
    b
}

fun whenExpression(flag: Int) {
    when (flag) {
        1 -> println("x == 1")
        2 -> println("x == 2")
        else -> { // Note the block
            println("flag is neither 1 nor 2")
        }
    }
}

fun whenExpression2(flag: Int) {
    when (flag) {
        0, 1 -> println("x == 0 or x == 1")
        else -> println("otherwise")
    }
}

fun whenExpression3(flag: Int) {
    var s = "2"
    when (flag) {
        parseInt(s) -> println("s encodes flag")
        else -> println("s does not encode flag")
    }
}

fun whenExpression4(number: Int) {
    var validNumbers = listOf(33, 55)
    when (number) {
        in 1..10 -> println("number is in the range")
        in validNumbers -> println("number is valid")
        !in 10..20 -> println("number is outside the range")
        else -> println("none of the above")
    }
}

fun hasPrefix(x: Any) = when(x) {
    is String -> x.startsWith("prefix")
    else -> false
}

fun forLoop(fruits: List<String>) {
    for (item in fruits) print(item + " ")
    println()
}

fun forLoop2(fruits: List<String>) {
    for (item: String in fruits) {
        print(item + " ")
    }
    println()
}

fun forLoop3(fruits: List<String>) {
    for (i in fruits.indices) {
        println(i.toString() + "번째 과일" + fruits[i])
    }
}

fun forLoop4(fruits: List<String>) {
    for ((index, value) in fruits.withIndex()) {
        println("the element at $index is $value")
    }
}