package com.oxyzgen.androidlearning.kotlin.stdlib

import kotlin.random.Random

/**
 * https://dev.to/kotlin/exploring-kotlin-lists-in-2021-2gi
 * 1 List Set Map
 * 2 loop with an Iterable
 * 3 高阶函数map: transforming collections
 * 4 其他高阶函数
 */
fun list(){

  val fruits = listOf(
    "Apple",
    "Banana",
    "Cherry",
    "Apple"
  )
  fruits.get(2)
  fruits[2] // Banana
  fruits.getOrNull(50) // null

  fruits.getOrElse(40) {
    println("There's no index $it!")
    "😔"
  }
// There's no index 3!
// 😔

  // for loop with an Iterable
  for(fruit in fruits) {
    println(fruit)
  }
  fruits.forEach { fruit ->
    println(fruit)
  }
// Apple
// Banana
// Cherry

/******** creating ****************/
  List(5) { idx -> "No. $idx" }
// [No. 0, No. 1, No. 2, No. 3, No. 4]

  "word-salad".toList()
// [w, o, r, d, -, s, a, l, a, d]

  mapOf(
    1 to "Gold",
    2 to "Silver",
    3 to "Bronze"
  ).toList()
// [(1, Gold), (2, Silver), (3, Bronze)]

  generateSequence {
    Random.nextInt(100).takeIf { it > 30 }
  }.toList()
// [73, 77, 69, 79, 71, 64]

  (0..10).toList()
// [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

  /********** slice **************/
  val myList = listOf("a", "b", "c", "d", "e")
  myList.slice(listOf(0, 2, 4)) // [a, c, e]
  myList.slice(0..3)
// [a, b, c, d]

  myList.slice(0..myList.lastIndex step 2)
// [a, c, e]

  myList.slice(2 downTo 0)
// [c, b, a]

  /********** Mutable Lists **************/
  mutableListOf(1, 2, 3)
// [1, 2, 3]

  (0..10).toMutableList() // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

  listOf(1, 2, 3).toMutableList() // [1, 2, 3]

  val m = mutableListOf(1, 2, 3)
  //add() +=  : both of which append an item to the end of the list.
  m.add(4)
  m += 4
  println(m) // [1, 2, 3, 4, 4]

  m.add(2, 10)
  println(m) // [1, 2, 10, 3, 4, 4]

  m += listOf(5, 6, 7)
  println(m) // [1, 2, 10, 3, 4, 4, 5, 6, 7]

  // remove function or the -= operator shorthand, which removes from our collection a single instance of the element we provide.
  m -= 3
  m.remove(3)
  m -= listOf(1, 4)
  m.removeAt(1)

  m[1] = 100

  /********** Fill and Clear ****************/
  val fruits2 = mutableListOf("🍉", "🍊", "🥝")
  fruits2.fill("🍬")
  println(fruits2) // [🍬, 🍬, 🍬]

  fruits2.clear()
  println(fruits2) // []

  /********** In-place modifications ****************/
  /*
  creating a new,don’t modify the original collection:sorted, shuffled, and reversed
  In-place modifications :sort, shuffle, and reverse
   */
  val list = listOf(3, 1, 4, 1, 5, 9)
  list.shuffled()
  list.sorted()
  list.reversed()
  println(list) // [3, 1, 4, 1, 5, 9]

  val m2 = list.toMutableList()
  m2.shuffle() // [5, 1, 1, 3, 4, 9]
  m2.sort() // [1, 1, 3, 4, 5, 9]
  m2.reverse() // [9, 5, 4, 3, 1, 1]

  // equivalents of the filter and filterNot functions
  val numbers = mutableListOf(3, 1, 4, 1, 5, 9)
  numbers.removeAll { it < 5 }
  println(numbers) // [5, 9]

  val letters = mutableListOf('a', 'b', '3', 'd', '5')
  letters.retainAll { it.isLetter() }
  println(letters) // [a, b, d]

  /********** Views on Lists ****************/
  val fruits3 = mutableListOf("🍉", "🍊", "🥝", "🍏")
  val sub = fruits3.subList(1, 4)
  println(sub) // [🍊, 🥝, 🍏]

  // 双向修改 Because this is only a view, and not a copy of our original collection, changes are automatically visible.
  fruits3[1] = "🍌"
  println(sub) // [🍌, 🥝, 🍏]
  sub[2] = "🍍"
  println(fruits) // [🍉, 🍌, 🥝, 🍍]
  sub.fill("🍬")
  println(fruits) // [🍉, 🍬, 🍬, 🍬]

  val fruits4 = mutableListOf("🍉", "🍊", "🥝", "🍏")
  val stiurf = fruits4.asReversed()
  println(stiurf) // [🍏, 🥝, 🍊, 🍉]
  fruits4[1] = "🍌"
  println(stiurf) // [🍏, 🥝, 🍌, 🍉]
  stiurf[2] = "🍍"
  println(fruits4) // [🍉, 🍍, 🥝, 🍏]

}

fun set(){
  val emotions = setOf(
    "Happy",
    "Curious",
    "Joyful",
    "Happy", // even if we try to add duplicates...
    "Joyful" // ...to our set...
  )

  println(emotions) // ...the elements in our set stay unique!
// [Happy, Curious, Joyful]
}

fun map(){
  val peopleToPizzaToppings = mapOf(
    "Ken" to "Pineapple",
    "Lou" to "Peperoni",
    "Ash" to "Ketchup"
  )

  println(peopleToPizzaToppings)
// {Ken=Pineapple, Lou=Peperoni, Ash=Ketchup}

  println(peopleToPizzaToppings["Ash"])
// Ketchup
}

// high order :  mapping, filtering, taking, dropping,Aggregating
fun transform(){
  val fruits = listOf(
    "Apple",
    "Banana",
    "Cherry"
  )
  val stiurf = fruits.map {
    it.reversed()
  }

  val strs = listOf(
    "1",
    "2",
    "three",
    "4",
    "V"
  )
  val nums: List<Int> = strs.mapNotNull {
    it.toIntOrNull()
  }
  println(nums)
// [1, 2, 4]

  val rank = listOf(
    "Gold",
    "Silver",
    "Bronze"
  )
  val ranking = rank.mapIndexed { idx, m ->
    "$m ($idx)"
  }
  println(ranking) //  [Gold (0), Silver (1), Bronze (2)]

  open class Person(val name: String, val age: Int) {
    override fun toString() = name
  }
  class Cyborg(name: String) : Person(name, 99)
  val people = listOf(
    Person("Joe", 15),
    Person("Agatha", 25),
    Person("Amber", 19),
    Cyborg("Rob"),
  )
  val discoVisitors = people.filter {
    it.age >= 18
  }// [Joe]
  val students = people.filterNot {
    it.age >= 18
  }// [Agatha, Amber, Rob]

  val (adults, children) = people.partition {
    it.age >= 18
  }
  println(adults) // [Agatha, Amber, Rob]

  val people2 = listOf(
    Person("Joe", 15),
    Person("Agatha", 25),
    Person("Amber", 19),
    Cyborg("Rob"),
    null
  )
  val actualPeople = people.filterNotNull()
  val cyborgs = people.filterIsInstance<Cyborg>()// [Rob]

  val objects = listOf("🌱", "🚀", "💡", "🐧", "⚙️")
  val seedlingAndRocket = objects.take(2)
  println(seedlingAndRocket) // [🌱, 🚀]
  val penguinAndGear = objects.drop(3)
  println(penguinAndGear) // [🐧, ⚙️]
  val nothing = objects.drop(8)
  println(nothing) // []
  println(objects) // remember, the original collection is not modified!
// [🌱, 🚀, 💡, 🐧, ⚙️]

  val randomNumbers = listOf(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 6)

  println(randomNumbers.average())
// 4.09090909090909091

  println(randomNumbers.sum())
// 45
  println(randomNumbers.minOrNull())
// 9

  println(randomNumbers.maxOrNull())
// 1

  val bigDigits = randomNumbers.count { it > 5 }

  println(bigDigits)
// 3
  val str = randomNumbers.joinToString (
    separator = "-",
    prefix = "pi://",
    limit = 5
  ) {
    "[$it]"
  }
  println(str)
// pi://[3]-[1]-[4]-[1]-[5]-...

  val randomNames = listOf("Dallas", "Kane", "Ripley", "Lambert")

  val cumulativeLength = randomNames.sumOf { it.length }

  println(cumulativeLength)
// 23
  val longestName = randomNames.maxOf { it.length }

  println(longestName)
// 7

  val shortestName = randomNames.minOf { it.length }

  println(shortestName)
// 4
}

// 谓词 判断
fun predicates(){
  data class Person(val name: String, val age: Int, val driversLicense: Boolean = false)

  val friendGroup = listOf(
    Person("Jo", 19),
    Person("Mic", 15),
    Person("Hay", 33, true),
    Person("Cal", 25)
  )
  val groupCanTravel = friendGroup.any { it.driversLicense } // true
  val groupGetsInClub = friendGroup.none { it.age < 18 } // false
  val groupHasShortNames = friendGroup.all { it.name.length < 4 } // true

  val nobody = emptyList<Person>()
  nobody.any { it.driversLicense } // false
  nobody.none { it.age < 18 } // true
  nobody.all { it.name.count() < 4 } // true
}

fun parts(){
  val objects = listOf("🌱", "🚀", "💡", "🐧", "⚙️", "🤖", "📚")
  println(objects.chunked(3))
// [[🌱, 🚀, 💡], [🐧, ⚙️, 🤖], [📚]]
  println(objects.chunked(3) { it.reversed() })
// [[💡, 🚀, 🌱], [🤖, ⚙️, 🐧], [📚]]

  // sliding window 滑动窗口
  println(objects.windowed(3))
// [[🌱, 🚀, 💡], [🚀, 💡, 🐧], [💡, 🐧, ⚙️], [🐧, ⚙️, 🤖], [⚙️, 🤖, 📚]]
  println(objects.windowed(4, 2, partialWindows = true))
// [[🌱, 🚀, 💡, 🐧], [💡, 🐧, ⚙️, 🤖], [⚙️, 🤖, 📚], [📚]]
  println(objects.windowed(4, 2, true) {
    it.reversed()
  })
// [[🐧, 💡, 🚀, 🌱], [🤖, ⚙️, 🐧, 💡], [📚, 🤖, ⚙️], [📚]]
}

// 降维
fun Un_nesting(){
  val objects = listOf("🌱", "🚀", "💡", "🐧", "⚙️", "🤖", "📚")
  objects.windowed(4, 2, true) {
    it.reversed()
  }.flatten()
// [🐧, 💡, 🚀, 🌱, 🤖, ⚙️, 🐧, 💡, 📚, 🤖, ⚙️, 📚]

  val lettersInNames = listOf("Lou", "Mel", "Cyn").flatMap {
    it.toList()
  }
  println(lettersInNames)
// [L, o, u, M, e, l, C, y, n]
}

/**
 * combine two collections
 * (keyList,valueList)  -->zip   Map
 * Map  -->unzip   (keyList,valueList)
 */
fun Combining(){
  val germanCities = listOf(
    "Aachen",
    "Bielefeld",
    "München"
  )

  val germanLicensePlates = listOf(
    "AC",
    "BI",
    "M"
  )

  // 类似衣服拉链
  println(germanCities.zip(germanLicensePlates))
// [(Aachen, AC), (Bielefeld, BI), (München, M)]
  println(germanCities zip germanLicensePlates)
// [(Aachen, AC), (Bielefeld, BI), (München, M)]

  println(germanCities.zip(germanLicensePlates) { city, plate ->
    city.uppercase() to plate.lowercase()
  })
// [(AACHEN, ac), (BIELEFELD, bi), (MÜNCHEN, m)]

  val citiesToPlates = germanCities.zip(germanLicensePlates) { city, plate ->
    city.uppercase() to plate.lowercase()
  }
  val (cities, plates) = citiesToPlates.unzip()
  println(cities) // [AACHEN, BIELEFELD, MÜNCHEN]
  println(plates) // [ac, bi, m]

  val random = listOf(3, 1, 4, 1, 5, 9, 2, 6, 5, 4)
  println(random.zipWithNext())
// [(3, 1), (1, 4), (4, 1), (1, 5), (5, 9), (9, 2), (2, 6), (6, 5), (5, 4)]

  val random1 = listOf(3, 1, 4, 1, 5, 9, 2, 6, 5, 4)
  val changes = random1.zipWithNext { a, b -> b - a }
  println(changes) // [-2, 3, -3, 4, 4, -7, 4, -1, -1]

}

/**
 * Custom aggregations: reduce and fold
 */
fun aggregations(){
  val random = listOf(3, 1, 4, 1, 5, 9, 2, 6, 5, 4)
  val multiplicativeAggregate = random.reduce { acc, value -> acc * value }
  println(multiplicativeAggregate) // 129600

  // fold 初始值
  val fruits = listOf("apple", "cherry", "banana", "orange")
  val multiplied = fruits.fold(1) { acc, value ->
    acc * value.length
  }
  println(multiplied) // 1080

  // Both fold and reduce come in a number of other flavors:
  //  reduceRight and foldRight
  // reduceOrNull allows you to work with empty collections without throwing exceptions.
  //runningFold and runningReduce don’t just return a single value representing the final state of the accumulator, but instead return a list of all the intermediate accumulator values as well
}










