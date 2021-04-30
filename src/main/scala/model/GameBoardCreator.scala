package model

class GameBoardCreator(size:Int) {
  def createBoard(): GameBoard = {
    var gameboard = new GameBoard(size)

    for {index <- 0 to 2} {
      for {index2 <- 0 to (size - 1)} {
        if ((index2 + index) % 2 == 0) {
          gameboard.set(size - index, index2 + 1, 1)
        }
      }
    }

    for {index <- 0 to 2} {
      for {index2 <- 1 to size} {
        if ((index2 + index) % 2 == 0) {
          gameboard.set(index + 1, index2, 2)
        }
      }
    }

  }

}
