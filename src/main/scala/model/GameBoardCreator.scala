package model

class GameBoardCreator(size:Int) {
  def createBoard(): GameBoard = {
    val gameboard = new GameBoard(size)

    for {index <- 1 to 3} {
      for {index2 <- 0 to (size - 1)} {
        if ((index2 + index) % 2 == 0) {
          gameboard.set(size - index, index2, 1)
        }
      }
    }
    for {index <- 0 to 2} {
      for {index2 <- 0 to (size - 1)} {
        if ((index2 + index) % 2 == 0) {
          gameboard.set(index, index2, 2)
        }
      }
    }
    return gameboard
  }
}
