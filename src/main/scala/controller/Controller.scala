package controller
import controller.{GameState}
import model.{GameBoard, GameBoardCreator, Piece}
import util.{Observable, UndoManager}

class Controller(var gameBoard:GameBoard) extends Observable {

  private val undoManager = new UndoManager
  var gameState: GameState = WhiteTurn(this)

  def createEmptyGameBoard(size: Int):Unit = {
    gameBoard = new GameBoard(size)
    notifyObservers
  }

  def createGameBoard(size: Int):Unit = {
    gameBoard = new GameBoardCreator(size).createBoard()
    notifyObservers
  }

  def setGameState(newGameState: GameState): Unit = {
    gameState = newGameState
  }

  def gameBoardToString: String = gameBoard.toString

  def set(row: Int, col: Int, piece: Piece):Unit = {
    gameBoard = gameBoard.set(row, col, piece)
    notifyObservers
  }

  def move(start: String, dest: String): Unit = {
    undoManager.doStep(new SetCommand(start, dest, this))
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