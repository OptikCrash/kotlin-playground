fun main(args: Array<String>) {
    println("Hello World!")
    println("sum of multiples of 3 or 5 up through 1000 = ${sumOfMultiples(3, 5, 1000)}")

    println("sum of even fibonacci numbers up to 4,000,000 = ${sumOfEvenFibonacci(4000000)}")
}

fun sumOfMultiples(first: Int, second: Int, limit: Int): Int {
    var i = 0
    var sum = 0
    while (i < limit) {
        if ( i % first == 0 || i % second == 0) {
            sum+= i
        }
        i++
    }
    println("sum = $sum")
    return sum
}

fun sumOfEvenFibonacci(limit: Int): Int {
    var sum : Int = 0
    fibonacciToN(limit).forEach {
        if (it % 2 == 0) {
            sum += it
        }
    }
    return sum
}

fun fibonacciToN(n: Int) : MutableList<Int> {
    var first = 0
    var second = 1
    var list = mutableListOf(first, second)
    while (first <= n) {
        val sum = first + second
        list.add(sum)
        first = second
        second = sum
    }
    return list
}

fun largestPrimeFactor(finalProduct: Int) {

}

fun primeToN(n: Int) : MutableList<Int> {
    val primes = mutableListOf<Int>()
    for (i in 1..n) {
        if (i.isPrime()) {
            primes.add(i)
        }
    }
    return primes
}

fun Int.isPrime() : Boolean {
    var flag = false
    for (i in 2..this / 2) {
        if (this % i == 0) {
            flag = true
            break
        }
    }
    return !flag
}

