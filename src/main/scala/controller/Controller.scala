package controller
import controller.GameState._
import model.{GameBoard, GameBoardCreator, Piece}
import util.UndoManager

import scala.swing.Publisher

class Controller(var gameBoard:GameBoard) extends Publisher {

  private val undoManager = new UndoManager
  var gameState: GameState = WHITE_TURN

  def createEmptyGameBoard(size: Int):Unit = {
    gameBoard = new GameBoard(size)
    publish(new FieldChanged)
  }

  def resize(newSize: Int): Unit = {
    gameBoard = new GameBoard(newSize)
    //gameState = RESIZE
    publish(new GBSizeChanged(newSize))
  }

  def createGameBoard(size: Int):Unit = {
    gameBoard = new GameBoardCreator(size).createBoard()
    //gameState = NEW
    publish(new FieldChanged)
  }

  def setGameState(newGameState: GameState): Unit = {
    gameState = newGameState
  }

  def gameBoardToString: String = gameBoard.toString

  def set(row: Int, col: Int, piece: Piece):Unit = {
    undoManager.doStep(new SetCommand(row, col, piece, this))
    gameBoard = gameBoard.set(row, col, piece)
    notifyObservers
  }

  def move(start: String, dest: String): Unit = {
    undoManager.doStep(new MoveCommand(start, dest, this))
  }

  def undo: Unit = {
    undoManager.undoStep
    notifyObservers
  }

  def redo: Unit = {
    undoManager.redoStep
    notifyObservers
  }

  def isSet(row: Int, col: Int): Boolean = gameBoard.field(row, col).isSet

  def field(row: Int, col: Int) = gameBoard.field(row,col)

  def gameBoardSize: Int = gameBoard.size


}