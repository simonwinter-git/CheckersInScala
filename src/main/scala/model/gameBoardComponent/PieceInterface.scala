package model.gameBoardComponent
import com.google.inject.ImplementedBy
import model.gameBoardComponent.gameBoardBaseImpl.{GameBoard, Piece}

@ImplementedBy(classOf[Piece])
trait PieceInterface {
  def state: String
  def row: Int
  def col: Int
  //def color: String

  def getColor: String

  def whiteMovePossible(to: String, gameBoard: GameBoard): Boolean
  def blackMovePossible(to: String, gameBoard: GameBoard): Boolean

  def movStrToInt(s: String): (Int, Int, Int, Int)



}
