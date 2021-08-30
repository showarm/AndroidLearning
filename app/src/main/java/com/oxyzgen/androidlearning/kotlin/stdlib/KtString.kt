package com.oxyzgen.androidlearning.kotlin.stdlib

import java.io.File

/**
 * https://dev.to/kotlin/kotlin-standard-library-safari-strings-3lj1
 * https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/
 * https://developer.android.google.cn/training/kotlinplayground
 */

fun hello() {
  println("Hello World!")
}

fun templates() {
  val name = "Johnathan"
  println("Hello, $name!") // Hello, Johnathan
  println("Your name is ${name.count()} long!") // Your name is 9 long!
}

fun multiline() {
  val myJson = """
    {
      "name": "jane",
      "lastname": "doe",
      "age": 29
    }
    """.trimIndent()
  println(myJson)
/* Prints:
{
  "name": "jane",
  "lastname": "doe",
  "age": 29
}
*/
}

/*
Kotlin can read terminal input via the readLine() function.
Or, we could read text from a file
 */
fun getString() {
  val fromStdIn = readLine()
  val fromFile = File("input.txt").readText()
}

fun buildStringFun(){
  val name = "Jane"
  val myString = buildString {
    repeat(10) {
      append("Hello, ")
      append(name)
      appendLine("!")
    }
  }
  println(myString)

/* Prints:
Hello, Jane!
Hello, Jane!
Hello, Jane!
Hello, Jane!
Hello, Jane!
Hello, Jane!
Hello, Jane!
Hello, Jane!
Hello, Jane!
Hello, Jane!
*/
}

/*
Extracting information from strings
 */
fun checkString(){
  println("   ".isBlank()) // true
  println("".isEmpty()) // true

  val neverBlankString = " ".ifBlank {
    "Never blank!"
  }
  println(neverBlankString) // Never blank!

  val input1 = "    valuable info "
  println(input1.trim()) // valuable info

  val input = "##placeholder##"
  println(input.removePrefix("##")) // placeholder##
  println(input.removeSuffix("##")) // ##placeholder
  println(input.removeSurrounding("##")) // placeholder

  println("a" < "b") // true
  println("c" < "a") // false

  val input2 = "QuICK brOWN fox"
  println(input2.equals("Quick Brown Fox", ignoreCase = true)) // true
}

fun stringCollections(){
  val input1 = "A; B; C; D; E"
  println(input1.split("; ", limit = 3)) // A, B, C; D; E

  // 去掉缩进
  val input2 = """
    Well this is crazy
    I'm a multiline string
    So split me maybe?
    """.trimIndent()
  println(input2.lines())
// [Well this is crazy, I'm a multiline string, So split me maybe?]

  val input = "Hello, World"
  println(input[1]) // e
  println(input.filter { it.isUpperCase() }) // HW


}





