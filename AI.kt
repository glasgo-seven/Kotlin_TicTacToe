import kotlin.random.Random

object AI {
	fun makeTurn(): String {
		var posWeight = Random.nextInt(Board.maxWeights.length) - 1
		if (posWeight < 0) {
			posWeight = 0
		}
		var pos = Board.maxWeights[posWeight].toString().toInt()
		Board.board[pos / 3][pos % 3].change(toO)
		return "${pos % 3 + 1}, ${pos / 3 + 1}"
	}

	fun setupWeights() {
		for (y in 0..2) {
			for (x in 0..2) {
				val pos = y + x
				Board.board[y][x].aiWeight = if (pos % 2 == 0) 0.75 else 0.5
			}
		}
		Board.board[1][1].aiWeight = 1.0
		Board.maxWeights = findMaxWeights()
	}

	fun findMaxWeights(): String {
		var maxWeight = 0.0
		for (y in 0..2) {
			for (x in 0..2) {
				if (Board.board[y][x].aiWeight > maxWeight && Board.board[y][x].aiWeight != 0.0) {
					maxWeight = Board.board[y][x].aiWeight
				}
			}
		}
		var maxWeightsStr = ""
		for (y in 0..2) {
			for (x in 0..2) {
				if (Board.board[y][x].aiWeight == maxWeight) {
					maxWeightsStr += "" + (y * 3 + x)
				}
			}
		}
		return maxWeightsStr
	}

	fun reWeights() {
		setupWeights()
		for (y in 0..2) {
			for (x in 0..2) {
				if (Board.board[y][x].isX || Board.board[y][x].isO) {
					for (y1 in y - 1..y + 1) {
						for (x1 in x - 1..x + 1) {
							if (y1 in 0..2 && x1 in 0..2) {
								if (!Board.board[y1][x1].isX && !Board.board[y1][x1].isO) {
									if (Board.board[y][x].isX) {
										Board.board[y1][x1].aiWeight -= 0.15
									} else {
										Board.board[y1][x1].aiWeight += 0.2
									}
								}
							}
						}
					}
				}
			}
		}
		Board.maxWeights = findMaxWeights()
	}
}