package model.gameBoardComponent.gameBoardBaseImpl

import util.Mover

case class Queen(state: String = "queen", row: Int, col: Int, getColor: String) extends Piece(state, row, col, getColor) {

  override def sList: List[String] = List("")
  override def toString: String = if (getColor == "black") "\uD83D\uDD34"//"\uD83D\uDFE0" //orange
  else "\uD83D\uDD35"//"\uD83D\uDFE3" //purple



  override def whiteMovePossible(to: String, gameBoard: GameBoard): Mover = new Mover(true, "", true)
  override def blackMovePossible(to: String, gameBoard: GameBoard): Mover = new Mover(true, "", true)
}
