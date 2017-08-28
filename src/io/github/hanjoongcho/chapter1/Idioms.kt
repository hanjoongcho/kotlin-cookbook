package io.github.hanjoongcho.chapter1

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

data class Customer(val name: String, val email: String) {

}

fun main(args: Array<String>) {

    // Creating DTOs (POJOs/POCOs)
    var customer = Customer("aaa", "bbb")
    println(customer.name)
    println(customer.email)

    // Default values for function parameters
    foo()
    foo(1)
    foo(2, "peach")

    // Filtering a list
    var list = listOf(-3, -1, 0, 1, 3, 5)
    val positives = list.filter { x -> x > 0 }
    val positives2 = list.filter { it > 0 }
    for (item in positives) {
        println(item)
    }
    for (index in positives2.indices) {
        println("$index: " + positives2[index])
    }

    // String Interpolation
    var name = "Hanjoong Cho"
    println("Name $name")

    // Instance Checks
    var int = 3
    var string = "Hello Kotlin"
    instanceCheck(int)
    instanceCheck(string)

    // Traversing a map/list of pairs
    var map = hashMapOf("key1" to "apple", "key2" to "banana")
    for ((k, v) in map) {
        println("$k -> $v")
    }

    // closed range: includes 100
    rangeType1()

    // half-open range: does not include 100
    rangeType2()
    rangeType3()
    rangeType4()
    rangeType5(13)

    // Read-only list
    val readOnlyList = listOf("a", "b", "c")

    // Read-only map
    val readOnlyMap = mapOf("a" to 1, "b" to 2, "c" to 3)

    // Accessing a map
    println(map["key1"])
    map["key3"] = "pineapple"
    println(map["key3"])

    // Lazy property
    val lazyValue: String by lazy {
        println("computed!")
        "Hello"
    }
    println(lazyValue)
    println(lazyValue)

    // Extension Functions
    fun String.spaceToCamelCase() {
        println("call String.spaceToCamelCase()")
    }
    "Convert this to camelcase".spaceToCamelCase()

    // Creating a singleton
//    object Resource {
//        val name = "Name"
//    }

    // If not null shorthand
    val files = File("Test").listFiles()
    println(files?.size)

    // If not null and else shorthand
    println(files?.size ?: "empty")

    // Executing a statement if null
    val data = mapOf("data1" to 1)
//    val email = data["email"] ?: throw IllegalStateException("Email is missing!")
//    println(email)

    // Execute if not null
    val data2 = "..."
    data2?.let {
        println("Execute if not null data2 is $data2")
    }

    // Map nullable value if not null
    val mapped = data?.let { transformData(it) } ?: -1
    println(mapped)

    // Return on when statement
    println("color number is " + transform("Green"))

    // 'try/catch' expression
    test()

    // 'if' expression
    println(foo(2))

    // Builder-style usage of methods that return Unit
    println("arrayOfMinusOnes size is " + arrayOfMinusOnes(10).size)

    // Single-expression functions
    println(theAnswer())
    println(theAnswer2())

    // Calling multiple methods on an object instance ('with')
    var myTurtle = Turtle()
    with(myTurtle) {
        penDown()
        penUp()
        turn(1.5)
        forward(3.7)
    }

    // Java 7's try with resources
    val stream = Files.newInputStream(Paths.get("/some/file.txt"))
    stream.buffered().reader().use { reader ->
        println(reader.readText())
    }

    // Convenient form for a generic function that requires the generic type information
//    inline fun <reified T: Any> Gson.fromJson(json: JsonElement): T = this.fromJson(json, T::class.java)

    // Consuming a nullable Boolean
//    val b: Boolean? = ...
//    if (b == true) {
//        ...
//    } else {
//        // `b` is false or null
//    }
}

class Turtle {
    fun penDown() {}
    fun penUp() {}
    fun turn(degrees: Double) {}
    fun forward(pixels: Double) {}
}

fun theAnswer() = 42

fun theAnswer2(): Int {
    return 42
}

fun arrayOfMinusOnes(size: Int): IntArray {
    return IntArray(size).apply { fill(-1) }
}

fun foo(param: Int): String {
    return if (param == 1) {
        "one"
    } else if (param == 2) {
        "two"
    } else {
        "three"
    }
}

fun test() {
    val result = try {
        throw ArithmeticException("test exception message")
    } catch (e: ArithmeticException) {
        println(e.message)
//        throw IllegalStateException(e)
    }

    // Working with result
}

fun transform(color: String): Int {
    return when (color) {
        "Red" -> 0
        "Green" -> 1
        "Blue" -> 2
        else -> throw IllegalArgumentException("Invalid color param value")
    }
}

fun transformData(map: Map<String, Int>): Map<String, Int> {
    println("called transformData from let")
    return map
}

fun rangeType1() {
    for (i in 1..100) {
        println(i)
    }
}

fun rangeType2() {
    for (i in 1 until 100) {
        println(i)
    }
}

fun rangeType3() {
    for (i in 2..10 step 2) {
        println(i)
    }
}

fun rangeType4() {
    for (i in 10 downTo 1) {
        println(i)
    }
}

fun rangeType5(num: Int) {
    if (num in 1..10) {
        print("$num is fit the range")
    }
}

fun foo(a: Int = 0, b: String = "apple") {
    println("Index $a is $b")
}

fun instanceCheck(any : Any) {
    when (any) {
        is Int    -> println("any is int")
        is String -> println("any is string")
        else      -> println("ant is etc")
    }
}