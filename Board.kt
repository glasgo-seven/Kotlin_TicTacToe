object Board {
	class Cell() {
		var symbol = ' '
		var isX = false
		var isO = false
		var aiWeight: Double = 0.0

		fun change(newSymbol: Char = ' ') {
			symbol = newSymbol
			isX = newSymbol == toX
			isO = newSymbol == toO
			aiWeight = 0.0
		}
	}

	val board = Array(3) {_ -> Array(3) {_ -> Cell()} }
	var maxWeights = ""

	fun printBoard() {
		println("┌1┬2┬3┐")
		println("1" + board[0][0].symbol + "|" + board[0][1].symbol + "|" + board[0][2].symbol + "│")
		println("├─┼─┼─┤")
		println("2" + board[1][0].symbol + "|" + board[1][1].symbol + "|" + board[1][2].symbol + "│")
		println("├─┼─┼─┤")
		println("3" + board[2][0].symbol + "|" + board[2][1].symbol + "|" + board[2][2].symbol + "│")
		println("└─┴─┴─┘")
	}

	fun printWeights() {
		println("" + board[0][0].aiWeight + "|" + board[0][1].aiWeight + "|" + board[0][2].aiWeight)
		println("\n" + board[1][0].aiWeight + "|" + board[1][1].aiWeight + "|" + board[1][2].aiWeight)
		println("\n" + board[2][0].aiWeight + "|" + board[2][1].aiWeight + "|" + board[2][2].aiWeight)
	}

	fun isEndGame(): Int {
		var row = ""
		for (y in 0..2) {
			row = "" + board[y][0].symbol + board[y][1].symbol + board[y][2].symbol
			if (row == "xxx" || row == "ooo") {
				if (board[y][0].isX) return 1
				else if (board[y][0].isO) return -1
			}
		}
		for (x in 0..2) {
			row = "" + board[0][x].symbol + board[1][x].symbol + board[2][x].symbol
			if (row == "xxx" || row == "ooo") {
				if (board[0][x].isX) return 1
				else if (board[0][x].isO) return -1
			}
		}
		row = "" + board[0][0].symbol + board[1][1].symbol + board[2][2].symbol
		if (row == "xxx" || row == "ooo") {
			if (board[1][1].isX) return 1
			else if (board[1][1].isO) return -1
		}
		row = "" + board[0][2].symbol + board[1][1].symbol + board[2][0].symbol
		if (row == "xxx" || row == "ooo") {
			if (board[1][1].isX) return 1
			else if (board[1][1].isO) return -1
		}
		return 0
	}
}