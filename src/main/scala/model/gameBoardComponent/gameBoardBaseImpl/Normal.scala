package model.gameBoardComponent.gameBoardBaseImpl

case class Normal(state: String = "normal", row: Int, col: Int, getColor: String) extends Piece(state, row, col, getColor) {
  override def toString: String = "\u25CD"
  override def whiteMovePossible(to: String, gameBoard: GameBoard): Boolean = true
  override def blackMovePossible(to: String, gameBoard: GameBoard): Boolean = true
}
