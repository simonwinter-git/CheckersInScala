package model.gameBoardComponent
import model.gameBoardComponent.gameBoardBaseImpl._
import util.Mode

trait GameBoardInterface {
  def size: Int
  def mode: Mode
  def getField(pos: String): FieldInterface
  def remove(row: Int, col: Int): GameBoardInterface
  def set(row: Int, col: Int, piece: Piece): GameBoardInterface
  def field(row: Int, col: Int): FieldInterface
  def colToInt(pos: String): Int
  def rowToInt(pos: String): Int
  def posToStr(row: Int, col: Int): String
  def setMode(mode: Mode): Unit
  def toString: String
  def getPiece(row: Int, col: Int): Option[PieceInterface]
  def move(start: String, dest: String): GameBoardInterface
  def whiteMovePossible(start: String, dest: String): Boolean
  def blackMovePossible(start: String, dest: String): Boolean
}

trait FieldInterface {
  def isSet: Boolean
  def toString: String
  def piece: Option[Piece]
}