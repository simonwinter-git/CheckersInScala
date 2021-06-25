package model.gameBoardComponent.gameBoardBaseImpl

case class Normal(state: String = "normal", row: Int, col: Int, getColor: String) extends Piece(state, row, col, getColor) {

  override def toString: String = if (getColor == "black") "\uD83D\uDD34" //red
    else "\uD83D\uDD35" //blue

  override def whiteMovePossible(to: String, gameBoard: GameBoard): Boolean = true
  override def blackMovePossible(to: String, gameBoard: GameBoard): Boolean = true
}
