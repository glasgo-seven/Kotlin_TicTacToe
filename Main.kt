import java.util.*

const val toX = 'x'
const val toO = 'o'

fun main() {
	val scanner = Scanner(System.`in`)
	var endGame = 0
	var playerTurn = true
	var turns = 0
	AI.setupWeights()

	while (endGame == 0 && turns != 9) {
		println()
		Board.printBoard()
		if (playerTurn) {
			println("Player's turn!")
			print("Input (x, y) to put X: ")
			var x = scanner.nextInt()
			var y = scanner.nextInt()
			while (!(x in 1..3 && y in 1..3) && Board.board[y - 1][x - 1].symbol != ' ') {
				println("Values are incorrect! Try again.")
				print("Input (x, y) to go: ")
				x = scanner.nextInt()
				y = scanner.nextInt()
			}
			Board.board[y - 1][x - 1].change(toX)
		} else {
			AI.reWeights()
			println("Computer's turn!")
			println("Computer put O at (${AI.makeTurn()}).")
		}
		playerTurn = !playerTurn
		turns++
		endGame = Board.isEndGame()
	}
	println()
	Board.printBoard()
	if (endGame == 1) {
		println("Player won!\nGame over.")
	} else if (endGame == -1) {
		println("Computer won!\nGame over.")
	} else {
		println("It's a tie!\n")
	}
}