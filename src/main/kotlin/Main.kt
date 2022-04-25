fun main(args: Array<String>) {
    println("Hello World!")
    println("sum of multiples of 3 or 5 up through 1000 = ${sumOfMultiples(3, 5, 1000)}")

    println("sum of even fibonacci numbers up to 4,000,000 = ${sumOfEvenFibonacci(4000000)}")
}

fun sumOfMultiples(first: Int, second: Int, limit: Int): Int {
    var i = 0
    var sum = 0
    val list = mutableListOf<Int>()
    while (i < limit) {
        if ( i % first == 0 || i % second == 0) {
            list.add(i)
            sum+= i
        }
        i++
    }
    println("list = $list")
    println("sum = $sum")
    return sum
}

fun sumOfEvenFibonacci(limit: Int): Int {
    var t1 = 0
    var t2 = 1
    var result = 0

    while (t1 <= limit) {
        val sum = t1 + t2
        t1 = t2
        t2 = sum
        if (sum % 2 == 0) {
            result+= sum
        }
    }
    return result
}