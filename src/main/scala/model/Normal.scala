package model

case class Normal(row: Int, col: Int, color: String) extends Piece {
  override def state: String = "normal"
  override def whiteMovePossible(to: String, gameBoard: GameBoard): Boolean = true
  override def blackMovePossible(to: String, gameBoard: GameBoard): Boolean = true
}
