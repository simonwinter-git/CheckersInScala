package model.gameBoardComponent.gameBoardBaseImpl

import util.Mover

case class Queen(state: String = "queen", row: Int, col: Int, getColor: String) extends Piece(state, row, col, getColor) {

  override def sList: List[String] = List("")
  override def toString: String = if (getColor == "black") "\uD83D\uDFE0"//"\uD83D\uDFE0" //orange/black
  else "\uD83D\uDFE3"//"\uD83D\uDFE3" //purple/white



  override def whiteMovePossible(to: String, gameBoard: GameBoard): Mover = new Mover(true, "", true)
  override def blackMovePossible(to: String, gameBoard: GameBoard): Mover = new Mover(true, "", true)
}
