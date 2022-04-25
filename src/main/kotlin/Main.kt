
fun main(args: Array<String>) {
    println("Hello World!")
    println("sum of multiples of 3 or 5 up through 1000 = ${sumOfMultiples(3, 5, 1000)}")
    println("sum of even fibonacci numbers up to 4,000,000 = ${sumOfEvenFibonacci(4000000)}")
    println("Largest Prime Factor of 13,195 is ${largestPrimeFactor(13195)}")
    println("prime factors of 600,851,475,143 are : ${primeFactors(600851475143)}")
    println("Largest Prime Factor of 600,851,475,143 is ${largestPrimeFactor(600851475143)}")
    println("largest palindrome from 3 digit numbers multiplied by 3 digit numbers is ${largestPalindrome()}")
    println("smallest multiple of 1 to 10 is ${smallestMultipleOfRange(1, 10)}")
//    println("smallest multiple of 1 to 20 is ${smallestMultipleOfRange(1, 20)}") /// takes a while to process...  232792560
    println("sum of squares 1 through 10 is ${sumOfSquares(1, 10)}")
    println("square of the sum of 1 through 10 is ${squareOfTheSum(1, 10)}")
    println("the difference between the sum of the squares and the square of the sum (1 - 10) is ${differenceBetweenSumOfSquareAndSquareOfSum(1, 10)}")
    println("the 6th prime number is ${nthPrime(6)}")
    println("the 10,001st prime number is ${nthPrime(10001)}")
    println("Largest product of 4 adjacent digits ${adjacentHighestProduct(4, thousandDigits())}")
    println("Largest product of 13 adjacent digits ${adjacentHighestProduct(13, thousandDigits())}")
    println("Largest product of 13 adjacent digits ${adjacentHighestProduct(13, thousand)}")
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
    val list = mutableListOf(first, second)
    while (second <= n) {
        val sum = first + second
        list.add(sum)
        first = second
        second = sum
    }
    return list
}

fun largestPrimeFactor(finalProduct: Long) : Int {
    return primeFactors(finalProduct).last()
}

fun primeFactors(finalProduct: Long) : MutableList<Int> {
    var number = finalProduct
    val factors = mutableListOf<Int>()
    var i = 2
    while (i <= number) {
        if (number % i == 0L) {
            factors.add(i)
            number /= i
            i--
        }
        i++
    }
    return factors
}

fun largestPalindrome() : Int {
    val first = 999
    val second = 999
    var firstPh = first
    val pairs = mutableListOf<Pair<Int, Int>>()
    val palindromes = hashMapOf<Int, Int>()
    while (firstPh > 1) {
        for (i in second downTo 1) {
            val ttl = firstPh * i
            if (ttl.toString().isPalindrome()) {
                val pair = Pair(firstPh, i)
                pairs.add(pair)
                palindromes[pairs.indexOf(pair)] = ttl
            }
        }
        firstPh--
    }
    return palindromes.maxOf { it.value }
}

fun smallestMultipleOfRange(first: Int, second: Int) : Int {
    var done = false
    val register = BooleanArray((first..second).count())
    var result = 1
    while (!done) {
        for (i in first..second) {
            register[i - 1] = result % i == 0
        }
        if (register.all { it }) {
            done = true
        } else {
            result++
        }
    }
    return result
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

fun String.isPalindrome() : Boolean {
    val sb = StringBuilder(this)
    val reverseStr = sb.reverse().toString()
    return this.equals(reverseStr, ignoreCase = true)
}

fun sumOfSquares(first: Int, second: Int) : Int {
    var sum = 0
    for (i in first..second) {
        sum += i * i
    }
    return sum
}

fun squareOfTheSum(first: Int, second: Int) : Int {
    var sum = 0
    for (i in first..second) {
        sum+= i
    }
    return sum * sum
}

fun differenceBetweenSumOfSquareAndSquareOfSum(first: Int, second: Int) : Int {
    return squareOfTheSum(first, second) - sumOfSquares(first, second)
}

fun nthPrime(n: Int) : Int {
    var i = 2
    val listOfPrimes = mutableListOf<Int>()
    while (listOfPrimes.size < n) {
        if (i.isPrime()) {
            listOfPrimes.add(i)
        }
        i++
    }
    return listOfPrimes[n - 1]
}

fun adjacentHighestProduct(adjacentCount: Int, listOfInts: List<Int>) : Int {
    var startIndex = 0
    var endIndex = adjacentCount - 1
    var setOfInts = mutableListOf<Int>()
    var product = 0
    while (endIndex <= listOfInts.lastIndex) {
        var tempProduct = 1
        val tempSetOfInts = mutableListOf<Int>()
        for (i in startIndex..endIndex) {
            tempSetOfInts.add(listOfInts[i])
            tempProduct*= listOfInts[i]
            if (tempProduct > product) {
                setOfInts = tempSetOfInts
                product = tempProduct
            }
        }
        startIndex++
        endIndex++
    }
    println("set: ${setOfInts.toList()} product: $product")
    return product
}

const val thousandDigitString = "7316717653133062491922511967442657474235534919493496983520312774506326239578318016984801869478851843858615607891129494954595017379583319528532088055111254069874715852386305071569329096329522744304355766896648950445244523161731856403098711121722383113622298934233803081353362766142828064444866452387493035890729629049156044077239071381051585930796086670172427121883998797908792274921901699720888093776657273330010533678812202354218097512545405947522435258490771167055601360483958644670632441572215539753697817977846174064955149290862569321978468622482839722413756570560574902614079729686524145351004748216637048440319989000889524345065854122758866688116427171479924442928230863465674813919123162824586178664583591245665294765456828489128831426076900422421902267105562632111110937054421750694165896040807198403850962455444362981230987879927244284909188845801561660979191338754992005240636899125607176060588611646710940507754100225698315520005593572972571636269561882670428252483600823257530420752963450"
fun thousandDigits() : List<Int> {
    val list = mutableListOf<Int>()
    thousandDigitString.toList().forEach { list.add(it.digitToInt()) }
    return list
}
val thousand = arrayListOf(7,3,1,6,7,1,7,6,5,3,1,3,3,0,6,2,4,9,1,9,2,2,5,1,1,9,6,7,4,4,2,6,5,7,4,7,4,2,3,5,5,3,4,9,1,9,4,9,3,4,
    9,6,9,8,3,5,2,0,3,1,2,7,7,4,5,0,6,3,2,6,2,3,9,5,7,8,3,1,8,0,1,6,9,8,4,8,0,1,8,6,9,4,7,8,8,5,1,8,4,3,
    8,5,8,6,1,5,6,0,7,8,9,1,1,2,9,4,9,4,9,5,4,5,9,5,0,1,7,3,7,9,5,8,3,3,1,9,5,2,8,5,3,2,0,8,8,0,5,5,1,1,
    1,2,5,4,0,6,9,8,7,4,7,1,5,8,5,2,3,8,6,3,0,5,0,7,1,5,6,9,3,2,9,0,9,6,3,2,9,5,2,2,7,4,4,3,0,4,3,5,5,7,
    6,6,8,9,6,6,4,8,9,5,0,4,4,5,2,4,4,5,2,3,1,6,1,7,3,1,8,5,6,4,0,3,0,9,8,7,1,1,1,2,1,7,2,2,3,8,3,1,1,3,
    6,2,2,2,9,8,9,3,4,2,3,3,8,0,3,0,8,1,3,5,3,3,6,2,7,6,6,1,4,2,8,2,8,0,6,4,4,4,4,8,6,6,4,5,2,3,8,7,4,9,
    3,0,3,5,8,9,0,7,2,9,6,2,9,0,4,9,1,5,6,0,4,4,0,7,7,2,3,9,0,7,1,3,8,1,0,5,1,5,8,5,9,3,0,7,9,6,0,8,6,6,
    7,0,1,7,2,4,2,7,1,2,1,8,8,3,9,9,8,7,9,7,9,0,8,7,9,2,2,7,4,9,2,1,9,0,1,6,9,9,7,2,0,8,8,8,0,9,3,7,7,6,
    6,5,7,2,7,3,3,3,0,0,1,0,5,3,3,6,7,8,8,1,2,2,0,2,3,5,4,2,1,8,0,9,7,5,1,2,5,4,5,4,0,5,9,4,7,5,2,2,4,3,
    5,2,5,8,4,9,0,7,7,1,1,6,7,0,5,5,6,0,1,3,6,0,4,8,3,9,5,8,6,4,4,6,7,0,6,3,2,4,4,1,5,7,2,2,1,5,5,3,9,7,
    5,3,6,9,7,8,1,7,9,7,7,8,4,6,1,7,4,0,6,4,9,5,5,1,4,9,2,9,0,8,6,2,5,6,9,3,2,1,9,7,8,4,6,8,6,2,2,4,8,2,
    8,3,9,7,2,2,4,1,3,7,5,6,5,7,0,5,6,0,5,7,4,9,0,2,6,1,4,0,7,9,7,2,9,6,8,6,5,2,4,1,4,5,3,5,1,0,0,4,7,4,
    8,2,1,6,6,3,7,0,4,8,4,4,0,3,1,9,9,8,9,0,0,0,8,8,9,5,2,4,3,4,5,0,6,5,8,5,4,1,2,2,7,5,8,8,6,6,6,8,8,1,
    1,6,4,2,7,1,7,1,4,7,9,9,2,4,4,4,2,9,2,8,2,3,0,8,6,3,4,6,5,6,7,4,8,1,3,9,1,9,1,2,3,1,6,2,8,2,4,5,8,6,
    1,7,8,6,6,4,5,8,3,5,9,1,2,4,5,6,6,5,2,9,4,7,6,5,4,5,6,8,2,8,4,8,9,1,2,8,8,3,1,4,2,6,0,7,6,9,0,0,4,2,
    2,4,2,1,9,0,2,2,6,7,1,0,5,5,6,2,6,3,2,1,1,1,1,1,0,9,3,7,0,5,4,4,2,1,7,5,0,6,9,4,1,6,5,8,9,6,0,4,0,8,
    0,7,1,9,8,4,0,3,8,5,0,9,6,2,4,5,5,4,4,4,3,6,2,9,8,1,2,3,0,9,8,7,8,7,9,9,2,7,2,4,4,2,8,4,9,0,9,1,8,8,
    8,4,5,8,0,1,5,6,1,6,6,0,9,7,9,1,9,1,3,3,8,7,5,4,9,9,2,0,0,5,2,4,0,6,3,6,8,9,9,1,2,5,6,0,7,1,7,6,0,6,
    0,5,8,8,6,1,1,6,4,6,7,1,0,9,4,0,5,0,7,7,5,4,1,0,0,2,2,5,6,9,8,3,1,5,5,2,0,0,0,5,5,9,3,5,7,2,9,7,2,5,
    7,1,6,3,6,2,6,9,5,6,1,8,8,2,6,7,0,4,2,8,2,5,2,4,8,3,6,0,0,8,2,3,2,5,7,5,3,0,4,2,0,7,5,2,9,6,3,4,5,0)