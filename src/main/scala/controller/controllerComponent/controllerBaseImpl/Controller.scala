package controller
import controller.controllerComponent.GameState._
import controller.controllerComponent.ControllerInterface
import model.gameBoardComponent.gameBoardBaseImpl.{GameBoardCreator, Piece, Field, GameBoard}
import controller.controllerComponent.GameState
import util.UndoManager

import scala.swing.Publisher

class Controller(var gameBoard:GameBoard) extends ControllerInterface with Publisher {

  private val undoManager = new UndoManager
  var gameState: GameState = WHITE_TURN

  def createEmptyGameBoard(size: Int): Unit = {
    gameBoard = new GameBoard(size)
    publish(new FieldChanged)
  }

  def resize(newSize: Int): Unit = {
    gameBoard = new GameBoard(newSize)
    //gameState = RESIZE
    publish(new GBSizeChanged(newSize))
  }

  def createGameBoard(size: Int): Unit = {
    gameBoard = new GameBoardCreator(size).createBoard()
    //gameState = NEW
    publish(new FieldChanged)
  }

  def setGameState(newGameState: GameState): Unit = {
    gameState = newGameState
  }

  def gameBoardToString: String = gameBoard.toString

  def set(row: Int, col: Int, piece: Piece): Unit = {
    undoManager.doStep(new SetCommand(row, col, piece, this))
    gameBoard = gameBoard.set(row, col, piece)
    publish(new FieldChanged)
  }

  def move(start: String, dest: String): Unit = {
    undoManager.doStep(new MoveCommand(start, dest, this))
    gameBoard = gameBoard.move(start, dest)
    publish(new FieldChanged)
  }

  def undo: Unit = {
    undoManager.undoStep
    publish(new FieldChanged)
  }

  def redo: Unit = {
    undoManager.redoStep
    publish(new FieldChanged)
  }

  def isSet(row: Int, col: Int): Boolean = gameBoard.field(row, col).isSet

  def field(row: Int, col: Int): Field = gameBoard.field(row,col)

  def gameBoardSize: Int = gameBoard.size

  def statusText: String = GameState.message(gameState)
  /*
  def highlight(index: Int): Unit = {
    gameBoard = gameBoard.highlight(index)
    publish(new FieldChanged)
  }
  */

}