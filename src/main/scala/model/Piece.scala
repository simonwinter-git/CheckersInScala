package model

trait Piece {
  def row: Int
  def col: Int
  def color: String
  def state: String

  def whiteMovePossible(to: String, gameBoard: GameBoard): Boolean
  def blackMovePossible(to: String, gameBoard: GameBoard): Boolean

  def movIntToStr(s: String): (Int, Int, Int, Int) = {
    val startCol = col
    val startRow = row
    val destCol = s.charAt(0).asDigit - 65
    val destRow = s.charAt(1).asDigit - 49
    (startRow, startCol, destRow, destCol)
  }
}

object Piece {

  def apply(state: String, row: Int, col: Int, color: String): Piece = state match {
    case "normal" => Normal(row, col, color)
    case "queen" => Queen(row, col, color)
  }


}