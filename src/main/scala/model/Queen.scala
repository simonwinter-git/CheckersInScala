package model

case class Queen(row: Int, col: Int, color: String) extends Piece {
  override def state: String = "queen"

}
