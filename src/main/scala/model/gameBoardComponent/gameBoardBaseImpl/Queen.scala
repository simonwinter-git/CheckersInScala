package model.gameBoardComponent.gameBoardBaseImpl

case class Queen(state: String = "queen", row: Int, col: Int, getColor: String) extends Piece(state, row, col, getColor) {
  override def toString: String = "Q"
  override def whiteMovePossible(to: String, gameBoard: GameBoard): Boolean = true
  override def blackMovePossible(to: String, gameBoard: GameBoard): Boolean = true
}
