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

  def setGameState(gameState: GameState):GameState {gameState}

  private val undoManager = new UndoManager

  def gameBoardToString: String = gameBoard.toString

  def set(row: Int, col: Int, piece: Piece):Unit = {
    gameBoard = gameBoard.set(row, col, piece)
    notifyObservers
  }

  def move(start: String, dest: String): Unit = {
    gameState match {
      case WhiteTurn =>
        if (gameBoard.whiteMovePossible((start, dest))) {
          gameBoard = gameBoard.move(start, dest)
          gameState = BlackTurn
        }
    }
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