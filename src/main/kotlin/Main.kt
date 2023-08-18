val list = mutableListOf<MutableList<String>>()
var soldTickets: Int = 0
var currentIncome: Int = 0
var totalIncome: Int = 0

fun main() {

    var correct: Boolean = false
    var rows = 0
    var seats = 0

    while (!correct) {
        println("Enter the number of rows:")
        rows = readln().toInt()

        if (rows <= 0) {
            println("Wrong input!")
            continue
        }

        println("Enter the number of seats in each row:")
        seats = readln().toInt()

        if (seats <= 0) {
            println("Wrong input!")
            continue
        }

        correct = true
    }

    for (i in 1..rows) {
        val seatList = mutableListOf<String>()
        list.add(seatList)
        for (j in 1..seats) {
            seatList.add("S")
        }
    }
    println()

    var isRunning: Boolean = true

    while (isRunning) {
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")

        when (readln()) {
            "0" -> isRunning = false
            "1" -> showSeats(seats, rows)
            "2" -> buyTicket(seats, rows)
            "3" -> printStats(seats, rows)
        }
    }
    showSeats(seats, rows)
}

fun printStats(seats: Int, rows: Int) {
    val percentage: Double = (soldTickets.toDouble() / (rows * seats).toDouble()) * 100
    val formatPercentage = "%.2f".format(percentage)
    val totalSeats = seats * rows
    if (totalSeats <= 60) {
        totalIncome = totalSeats * 10
    } else {
        if (rows % 2 != 0) {
            val seats1 = ((rows / 2 + 1) * seats) * 8
            val seats2 = ((rows / 2) * seats) * 10
            totalIncome = seats1 + seats2
        } else {
            totalIncome = (rows / 2) * seats * 10 + (rows / 2) * 8 * seats
        }
    }



    println("Number of purchased tickets: $soldTickets")
    println("Percentage: $formatPercentage%")
    println("Current income: $$currentIncome")
    println("Total income: $$totalIncome")
}

fun showSeats(seats: Int, rows: Int) {
    println()
    println("Cinema:")
    print(" ")

    for (i in 1..seats) {
        print(" $i")
    }

    for (i in 1..rows) {
        print("\n$i")
        for (j in 0..seats - 1) {
            val letter = list[i - 1][j]
            print(" $letter")
        }
    }
    println("\n")
}

fun buyTicket(seats: Int, rows: Int) {
    var done: Boolean = false;

    while (!done) {
        println("Enter a row number:")
        val selectedRow = readln().toInt()

        println("Enter a seat number in that row:")
        val selectedSeat = readln().toInt()

        if (selectedRow > rows || selectedSeat > seats || selectedRow <= 0 || selectedSeat <= 0) {
            println("Wrong input!")
            continue
        }

        if (list[selectedRow - 1][selectedSeat - 1] == "B") {
            println("That ticket has already been purchased!")
            continue;
        }

        val totalSeats: Int = rows * seats
        val chosenSeat = selectedRow
        val chosenSeatPrice: Int
        soldTickets++
        if (totalSeats <= 60) {
            totalIncome = totalSeats * 10
            chosenSeatPrice = 10
        } else {
            if (rows % 2 != 0) {
                if (chosenSeat in ((rows / 2) + 1)..rows) chosenSeatPrice = 8 else chosenSeatPrice = 10
                val seats1 = ((rows / 2 + 1) * seats) * 8
                val seats2 = ((rows / 2) * seats) * 10
                totalIncome = seats1 + seats2
            } else {
                totalIncome = (rows / 2) * seats * 10 + (rows / 2) * 8 * seats
                if (chosenSeat in ((rows / 2))..rows) chosenSeatPrice = 8 else chosenSeatPrice = 10
            }
        }
        list[selectedRow - 1][selectedSeat - 1] = "B"
        println()
        println("Ticket price: $$chosenSeatPrice")
        currentIncome += chosenSeatPrice
        println()
        done = true
    }
}
