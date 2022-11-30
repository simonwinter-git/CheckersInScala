package checkers.model.gameBoardComponent
import com.google.inject.ImplementedBy
import checkers.model.gameBoardComponent.gameBoardBaseImpl.{GameBoard, Piece}
import checkers.util.Mover

import scala.collection.mutable.ListBuffer

@ImplementedBy(classOf[Piece])
trait PieceInterface {
  def sList: ListBuffer[String]
  def sListBlack: ListBuffer[String]
  def state: String
  def row: Int
  def col: Int

  def getColor: String

  def whiteMovePossible(to: String, gameBoard: GameBoard): Mover
  def blackMovePossible(to: String, gameBoard: GameBoard): Mover

  def posToStr(row: Int, col: Int): String
  def movStrToInt(s: String): (Int, Int, Int, Int)



}
