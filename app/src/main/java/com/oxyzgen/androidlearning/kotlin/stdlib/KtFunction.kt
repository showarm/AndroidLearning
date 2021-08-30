package com.oxyzgen.androidlearning.kotlin.stdlib

/*
https://www.geeksforgeeks.org/kotlin-programming-language/
https://www.geeksforgeeks.org/kotlin-functions/

In Kotlin, there are two types of function-

Standard library function
User defined function
 */

//1 函数定义
/**
 * 标准库函数
sqrt() – Used to calculate the square root of a number.
print() – Used to print a message to standard output.
rem() – To find the remainder of one number when divided by another.
toInt() – To convert a number to integer value.
readline() – Used for standard input.
compareTo() – To compare two numbers and return boolean value.
 */
fun main1(args: Array<String>) {
  var sum = arrayOf(1,2,3,4,5,6,7,8,9,10).sum()
  println("The sum of all the elements of an array is: $sum")

  var num1 = 26
  var num2 = 3
  var result = num1.rem(num2)
  println("The remainder when $num1 is divided by $num2 is: $result")
  // The remainder when 26 is divided by 3 is: 2
}

/**
 * 自定义函数
fun fun_name(a: data_type, b: data_type, ......): return_type  {
// other codes
return
}
 */
fun mul(num1: Int, num2: Int): Int {
  var number = num1.times(num2)
  return number
}

//2 函数参数
//  default arguments in function definition name,standard and roll_no
fun student( name: String="Praveen", standard: String="IX" , roll_no: Int=11 ) {
  println("Name of the student is: $name")
  println("Standard of the student is: $standard")
  println("Roll no of the student is: $roll_no")
}
fun main2(args: Array<String>) {
  val name_of_student = "Gaurav"
  val standard_of_student = "VIII"
  val roll_no_of_student = 25

  // 默认参数按顺序 passing only two arguments name and standard of student
  student(name_of_student,standard_of_student)

  // 指定参数name passing the arguments with name as defined in function
  student(name=name_of_student,roll_no=roll_no_of_student)
}

// 3 函数调用：普通调用，回调
// Kotlin program of factorial using recursion
fun Fact(num: Int):Long{
  // terminate condition 结束条件
  // 如果没有结束条件：Exception in thread "main" java.lang.StackOverflowError
  return if(num==1) num.toLong()
  else num*Fact(num-1)
}
//main method
fun main3() {
  //Recursive call
  println("Factorial of 5 is: "+Fact(5)) // Factorial of 5 is: 120
}
/* 尾递归
In tail recursion, function call is the last thing executed by the function and nothing left in the current function to execute. So, there is no need to save current function call in stack memory and compiler can re-use that stack space for next recursive call.
In tail recursion, we do not get the StackOverflowError during the execution of program.
 */
// Kotlin program of factorial using tail-recursion
fun Fact(num: Int, x:Int):Long{

  return if(num==1)   // terminate condition
    x.toLong()
  else
    Fact(num-1,x*num)   //tail recursion
}
fun main4() {
  var n = 1
  var result = Fact(5,n)
  println("Factorial of 5 is: $result")
}

// 4 Lambdas Expressions and Anonymous Functions
/**
 * Syntax of Lambda expression –
 * val lambda_name : Data_type = { argument_List -> code_body }
 * 如果返回类型不是Unit，则最后一行的结果为返回值
 */
val sum = {a: Int , b: Int -> a + b}  // 返回值 类型推断
val sum1:(Int,Int) -> Int = { a, b -> a + b}
//val sum2 = { a, b -> a + b}
val lambda3: (Int)-> Unit = {print(Int)}

// 类型扩展
val lambda4: String.(Int) -> String = {this + it}
val result = "Geeks".lambda4(50)  // Geeks50
//this keyword is used for the string and it keyword is used for the Int parameter passed in the lambda
// it 是implicit name of a single parameter

// https://www.geeksforgeeks.org/kotlin-lambdas-expressions-and-anonymous-functions/
// TODO  这尼玛不是匿名函数吗
val find =fun(num: Int): String{
  if(num % 2==0 && num < 0) {
    return "Number is even and negative"
  }
  else if (num %2 ==0 && num >0){
    return "Number is even and positive"
  }
  else if(num %2 !=0 && num < 0){
    return "Number is odd and negative"
  }
  else {
    return "Number is odd and positive"
  }
}
val result2 = find(112)


var lambda = {
  println("Lambda expression")
  // normally lambda expression does not allow return statement, so gives compile time error
//  return
}
/**
 * 匿名函数
 * Difference between lambda expressions and anonymous functions-
The only difference is the behavior of non-local returns.
A return statement without a label always returns from the function declared with the fun keyword.
This means that a return inside a lambda expression will return from the enclosing function,
whereas a return inside an anonymous function will return from the anonymous function itself.
 也就是说，匿名函数是本身return，lambda表达式的{}不能包含直接return，只能在{{}}中出现return
 */
// anonymous function with body as an expression
val anonymous1 = fun(x: Int, y: Int): Int = x + y
// anonymous function with body as a block
val anonymous2 = fun(a: Int, b: Int): Int {
  val mul = a * b
  return mul
}
fun main5(args: Array<String>) {
  //invoking functions
  val sum = anonymous1(3,5)
  val mul = anonymous2(3,5)
  println("The sum of two numbers is: $sum")
  println("The multiply of two numbers is: $mul")
}

/*全是内存中的object
In Kotlin, the higher-order functions or lambda expressions,
all stored as an object so memory allocation, for both function objects and classes, and virtual calls
might introduce runtime overhead.
  inline 内敛声明，让编译器把被调用的代码复制到当前函数栈，而不是单独分配内存
use the inline keyword which ultimately requests the compiler to not allocate memory
and simply copy the inlined code of that function at the calling place.
 */
// 5 Inline Functions 内联函数
fun main6(args: Array<String>){
  println("Main function starts")
  inlinedFunc(
    // 内联函数里的lambda表达式可以return，正常是不行的
    {
      println("Lambda expression 1")
      return
    },
    {
      println("Lambda expression 2")
    }
  )
  println("Main function ends")
  /*输出
Main function starts
Lambda expression 1
   */

  genericFunc<String>() // class kotlin.String
}
// 但是 crossinline 不允许lambda表达式return
// 而 noinline 直接不让该参数以内敛方式调用
inline fun inlinedFunc(
  /* crossinline */lmbd1: () -> Unit,
  /* noinline */lmbd2: () -> Unit, ) {
  lmbd1()
  lmbd2()
}
inline fun <reified T> genericFunc() {
  print(T::class)
}

fun main7(args: Array<String>) {
  print(flag) // true
}
fun foo(i: Int ): Int{
  var a = i
  return a
}
inline var flag : Boolean
  get() = foo(10 ) == 10
  set(flag) {flag}

// 6 infix notation 中缀表达式：内置的、自定义
// 还有一部分 运算符重载
class check{
  // user defined infix member function
  infix fun dataType(x: Any):Any{
    var i = when(x){
      is String -> "String"
      is Int -> "Integer"
      is Double -> "Double"
      else -> "invalid"
    }
    return i
  }
}
fun main8(args: Array<String>){
  var chk = check()
  // call using infix notation
  var result = chk dataType 3.3
  println(result) // Double
}

//functional programming
// 7 Higher-Order Functions
/*
a function which can accepts a function as parameter or can returns a function is called Higher-Order function
 */
var lambda7 = {a: Int , b: Int -> a + b }
fun mul1(a: Int, b: Int): Int{
  return a*b
}
fun higherfunc1(funcParam:(Int,Int)-> Int){
  funcParam(3,6)
}
fun higherfunc() : ((Int,Int)-> Int){
  return ::mul1
}
fun main(args: Array<String>) {
  higherfunc1(lambda7)
  higherfunc1(::mul1)
  higherfunc()(2,4)
}












