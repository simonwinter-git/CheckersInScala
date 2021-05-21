package controller
import controller.GameState._
import model.{GameBoard, Piece}
import util.{Observable, UndoManager}

class Controller(var gameBoard:GameBoard) extends Observable {

  var gameState: GameState = IDLE
  def createEmptyGameBoard(size: Int):Unit = {
    gameBoard = new GameBoard(size)
    notifyObservers
  }

  private val undoManager = new UndoManager

  def gameBoardToString: String = gameBoard.toString

  def set(row: Int, col: Int, piece: Piece):Unit = {
    gameBoard = gameBoard.set(row, col, piece)
    notifyObservers
  }

  def move() = {

  }

  def undo: Unit = {
    undoManager.undoStep
    notifyObservers
  }

  def redo: Unit = {
    undoManager.redoStep
    notifyObservers
  }

}