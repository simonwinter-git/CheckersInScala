package model.gameBoardComponent
import com.google.inject.ImplementedBy
import model.gameBoardComponent.gameBoardBaseImpl.Piece

@ImplementedBy(classOf[Piece])
trait PieceInterface {
  def state: String
  def row: Int
  def col: Int
  def color: String

  def getColor: String

  def whiteMovePossible: Boolean
  def blackMovePossible: Boolean

  def movStrToInt: (Int, Int, Int, Int)



}
