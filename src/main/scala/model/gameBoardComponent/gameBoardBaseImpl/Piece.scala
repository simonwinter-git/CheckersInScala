package model.gameBoardComponent.gameBoardBaseImpl

trait Piece {
  def row: Int
  def col: Int
  def color: String
  def state: String

  def whiteMovePossible(to: String, gameBoard: GameBoard): Boolean
  def blackMovePossible(to: String, gameBoard: GameBoard): Boolean

  def movStrToInt(s: String): (Int, Int, Int, Int) = {
    val startCol = col
    val startRow = row
    val destRow = s.charAt(1).toInt - 49            //Zahl
    val destCol = s.charAt(0).toInt - 65          //Buchstabe
    (startRow, startCol, destRow, destCol)
  }
}

object Piece {

  def apply(state: String, row: Int, col: Int, color: String): Piece = state match {
    case "normal" => Normal(row, col, color)
    case "queen" => Queen(row, col, color)
  }


}