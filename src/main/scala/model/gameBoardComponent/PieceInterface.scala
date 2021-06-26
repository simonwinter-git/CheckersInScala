package model.gameBoardComponent
import com.google.inject.ImplementedBy
import model.gameBoardComponent.gameBoardBaseImpl.{GameBoard, Piece}
import util.Mover

@ImplementedBy(classOf[Piece])
trait PieceInterface {
  def sList: List[String]
  def state: String
  def row: Int
  def col: Int
  //def color: String

  def getColor: String

  def whiteMovePossible(to: String, gameBoard: GameBoard): Mover
  def blackMovePossible(to: String, gameBoard: GameBoard): Mover

  def posToStr(row: Int, col: Int): String
  def movStrToInt(s: String): (Int, Int, Int, Int)



}
