package controller
import model.{GameBoard}
import util.Observable

class Controller(var gameBoard:GameBoard) extends Observable{
  def createEmptyGameBoard(size: Int):Unit = {
    gameBoard = new GameBoard(size)
    notifyObservers
  }

  def gameBoardToString: String = gameBoard.toString

  def set(row: Int, col: Int, state: Int):Unit = {
    gameBoard = gameBoard.set(row, col, state)
    notifyObservers
  }

}