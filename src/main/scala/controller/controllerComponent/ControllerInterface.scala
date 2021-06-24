package controller.controllerComponent
import controller.controllerComponent.GameState.GameState
import model.gameBoardComponent.FieldInterface
import model.gameBoardComponent.gameBoardBaseImpl.Piece

import scala.swing.Publisher

trait ControllerInterface extends Publisher {

  def gameState: GameState
  def createEmptyGameBoard(size: Int): Unit
  def createNewGameBoard: Unit
  def resize(newSize: Int): Unit
  def createGameBoard(size: Int): Unit
  def setGameState(newGameState: GameState): Unit
  def gameBoardToString: String
  def set(row: Int, col: Int, piece: Piece): Unit
  def move(start: String, dest: String): Unit
  def undo: Unit
  def redo: Unit
  def isSet(row: Int, col: Int): Boolean
  def field(row: Int, col: Int): FieldInterface
  def gameBoardSize: Int
  def statusText: String

}

import scala.swing.event.Event

class FieldChanged extends Event
case class GBSizeChanged(newSize: Int) extends Event