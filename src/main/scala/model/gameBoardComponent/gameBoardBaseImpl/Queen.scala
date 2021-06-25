package model.gameBoardComponent.gameBoardBaseImpl

case class Queen(state: String = "queen", row: Int, col: Int, getColor: String) extends Piece(state, row, col, getColor) {

  override def toString: String = if (getColor == "black") "\uD83D\uDFE0" //orange
  else "\uD83D\uDFE3" //purple



  override def whiteMovePossible(to: String, gameBoard: GameBoard): Boolean = true
  override def blackMovePossible(to: String, gameBoard: GameBoard): Boolean = true
}
