
fun main() {

    // Part 1
    val matches = (359282..820401)
        .filter(::containsSequenceOfAtLeastTwoNumbers)
        .filter(::numbersNeverDecrease)
    println(matches.size)

    // Part 2
    val matches2 = matches.filter(::containsSequenceOfExactlyTwoNumbers)
    println(matches2.size)

}

fun containsSequenceOfAtLeastTwoNumbers(i: Int): Boolean =
    i.toString().matches(Regex(""".*(.)\1.*"""))

// I feel like this criteria was worded VERY badly
// This function also only works on a string of increasing numbers (which is ok in this case ^^)
fun containsSequenceOfExactlyTwoNumbers(i: Int): Boolean {
    return i.toString()
        .groupBy { it }
        .mapValues { it.value.size }
        .filterValues { it > 1 }
        .containsValue(2)
}

fun numbersNeverDecrease(i: Int): Boolean {
    return i.toString() == i.toString().toCharArray().sorted().joinToString("")
}
