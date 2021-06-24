package model.gameBoardComponent.gameBoardMockImpl
import controller.controllerComponent.GameState.WHITE_TURN
import model.gameBoardComponent.gameBoardBaseImpl.{Classic, Piece}
import model.gameBoardComponent.{FieldInterface, GameBoardInterface, PieceInterface}
import util.Mode

class GameBoard(var size: Int) extends GameBoardInterface {
  size = 3
  def mode: Mode = Classic()

  def getField(pos: String): FieldInterface = EmptyField

  def remove(row: Int, col: Int): GameBoardInterface = this

  def set(row: Int, col: Int, piece: Piece): GameBoardInterface = this

  def field(row: Int, col: Int): FieldInterface = EmptyField

  def colToInt(pos: String): Int = 0

  def rowToInt(pos: String): Int = 0

  def posToStr(row: Int, col: Int): String = "A1"

  def setMode(mode: Mode): Unit = WHITE_TURN

  override def toString: String = "\n+-------+\n|       |\n|       |\n|       |\n+-------+\n"

  def getPiece(row: Int, col: Int): Option[PieceInterface] = None

  def move(start: String, dest: String): GameBoardInterface = this

  def whiteMovePossible(start: String, dest: String): Boolean = true

  def blackMovePossible(start: String, dest: String): Boolean = true
}

object EmptyField extends FieldInterface {
  def isSet: Boolean = false
  def piece: Option[Piece] = None

  override def toString: String = " "
}
