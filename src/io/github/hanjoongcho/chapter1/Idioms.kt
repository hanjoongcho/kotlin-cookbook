package io.github.hanjoongcho.chapter1

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