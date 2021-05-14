package controller
import model.{GameBoard}
import util.Observable

class Controller(var gameBoard:GameBoard) extends Observable{
  def createEmptyGameBoard(size: Int):Unit = {
    gameBoard = new GameBoard(size)
    notifyObservers
  }

  def gameBoardToString: String = gameBoard.toString

  def set(row: Int, col: Int, color: Int, state: Int):Unit = {
    gameBoard = gameBoard.set(row, col, color, state)
    notifyObservers
  }

  def move(startRow: Int, startCol: Int, destRow: Int, destCol: Int, color: Int, state: Int) = {

    if (color == 1 && destRow == 0 && state == 1) {
      gameBoard = gameBoard.set(startRow, startCol, 0, 0)
      gameBoard = gameBoard.set(destRow, destCol, color, 2)
    }

    if (color == 2 && destRow == (gameBoard.size - 1) && state == 1) {
      gameBoard = gameBoard.set(startRow, startCol, 0, 0)
      gameBoard = gameBoard.set(destRow, destCol, color, 2)
    }

  }

}