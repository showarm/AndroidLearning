package com.oxyzgen.androidlearning.kotlin.stdlib

/** https://www.geeksforgeeks.org/introduction-to-kotlin/

Key Features of Kotlin:

1 Statically typed – 静态类型，编译时就能知道数据类型
2 Data Classes– auto-generation of boilerplate like equals, hashCode, toString, getters/setters and much more.
/*     Java Code     */
class Book {
  private String title;
  private Author author;
  public String getTitle()
  {
  return title;
  }
  public void setTitle(String title)
  {
  this.title = title;
  }
  public Author getAuthor()
  {
  return author;
  }
  public void setAuthor(Author author)
  {
  this.author = author;
  }
}
But in Kotlin only one line used to define the above class –
/* Kotlin Code */
  data class Book(var title:String, var author:Author)
3 Concise简洁  It drastically reduces the extra code written in other object-oriented programming languages.
4 Safe – 安全 系统性支持nullability，避免NullPointerExceptions
  Every variable in Kotlin is non-null by default.
  String s = "Hello Geeks"    // Non-null
  s = null                    // If we try to assign s null value then it gives compile time error
  s.length() function also disabled on the nullable strings.
  //  To assign null value to any string string it should be declared as nullable.
  String nullableStr? = null  // compiles successfully

5 Interoperable with Java – Kotlin runs on Java Virtual Machine(JVM) so it is totally interoperable with java.
6 Functional and Object Oriented Capabilities – Kotlin has rich set of many useful methods which includes
  higher-order functions, lambda expressions, operator overloading, lazy evaluation,and much more.
7 Smart Cast – It explicitly typecasts the immutable values and inserts the value in its safe cast automatically.
  If we try to access a nullable type of String ( String? = “BYE”) without safe cast it will generate a compile error.
    fun main(args: Array){
      var string: String? = "BYE"
      print(string.length)       // compile time error
    }
    fun main(args: Array){
      var string: String? = "BYE"
      if(string != null) {               // smart cast
        print(string.length)
      }
    }
8 Compilation time – It has higher performance and fast compilation time.
9 Tool- Friendly – It has excellent tooling support. Any of the Java IDEs – IntelliJ IDEA, Eclipse and Android Studio can be used for Kotlin. We can also be run Kotlin program from command line.
 */

//1 Data Types
/* 所有数据类型都是包装类型
  The most fundamental data type in Kotlin is Primitive data type
  and all others are reference types like array and string.
  Java needs to use wrappers (java.lang.Integer) for primitive data types to behave like objects
  but Kotlin already has all data types as objects.
4种
Integer Data type
Floating-point Data Type
Boolean Data Type
Character Data Type
 */
/* Integer
Data Type	Bits	Min Value	Max Value
byte	8 bits	-128	127
short	16 bits	-32768	32767
int	32 bits	-2147483648	2147483647
long	64 bits	-9223372036854775808	9223372036854775807
 */
/* Floating-point
float	32 bits	1.40129846432481707e-45	3.40282346638528860e+38
double	64 bits	4.94065645841246544e-324	1.79769313486231570e+308
 */
/* Boolean
boolean	1 bit	true or false
 */
/* Character
char	8 bits	-128	127
 */

// 2 Variables
var rollno = 55
val myName = "Gaurav"  // Immutable is also called read-only variables
var a ={
  rollno = 26
//  myName = "ee"
}
/* 不可变类型 不是常量
Immutable variable is not a constant because it can be initialized with the value of a variable.
It means the value of immutable variable doesn’t need to be known at compile-time,
and if it is declared inside a construct that is called repeatedly,
it can take on a different value on each function call.
 */
var myBirthDate = "02/12/1993"
val myNewBirthDate = myBirthDate

// 3 Operators
/*
Arithmetic operator
Relation operator
Assignment operator
Unary operator
Logical operator
Bitwise operator
 */
/* Arithmetic
+	Addition	a + b	a.plus(b)
–	Subtraction	a – b	a.minus(b)
*	Multiplication	a * b	a.times(b)
/	Division	a / b	a.div(b)
%	Modulus	a % b	a.rem(b)
 */
/* Relation
>	greater than	a > b	a.compareTo(b) > 0
<	less than	a < b	a.compareTo(b) < 0
>=	greater than or equal to	a >= b	a.compareTo(b) >= 0
<=	less than or equal to	a <= b	a.compareTo(b) <= 0
==	is equal to	a == b	a?.equals(b) ?: (b === null)
!=	not equal to	a != b	!(a?.equals(b) ?: (b === null)) > 0
 */
/* Assignment
+=	a = a + b	a.plusAssign(b) > 0
-=	a = a - b	a.minusAssign(b) < 0
*=	a = a * b	a.timesAssign(b)>= 0
/=	a = a / b	a.divAssign(b) <= 0
%=	a = a % b	a.remAssign(b)
 */
/* Unary
++	++a or a++	a.inc()
--	--a or a--	a.dec()
 */
/* Logical
&&	return true if all expressions are ture	(a>b) && (a>c)
||	return true if any of expression is true	(a>b) || (a>c)
!	return complement of the expression	a.not()
 */
/* Bitwise
shl	signed shift left	a.shl(b)
shr	signed shift right	a.shr(b)
ushr	unsigned shift right	a.ushr()
and	bitwise and	a.and(b)
or	bitwise or	a.or()
xor	bitwise xor	a.xor()
inv	bitwise inverse	a.inv()
 */

// 4 Standard Input/Output
fun main(args: Array<String>) {
  print("Enter a double value: ")
  val string2= readLine()!!
  // .toDouble() function converts the string into Double
  var doubleValue: Double = string2.toDouble()
  println("You entered: $doubleValue")
}

// 5 Type Conversion (also called as Type casting)
var myNumber = 100
//var myLongNumber: Long = myNumber   // Compiler error  Java可以，kotlin不行
// Type mismatch: inferred type is Int but Long was expected

var myLong = myNumber.toLong()

//6 Expression, Statement and Block
var sum2 = 10 + 20 // 10 + 20 is an expression but var sum = 10 + 20 is a statement
/*
In Java, if is a statement but, in Kotlin if is an expression.
It is called an expression because it compares the values of a and b and returns the maximum value.
Therfore, in Kotlin there is no ternary operator (a>b)?a:b because it is replaced by the if expression.
 */
fun mainEsb(args: Array<String>){
  val a = 1000
  val b = 999
  var max1 = if(a > b) a else b
  println("The maximum of ${a} and ${b} is $max1 " )
}















