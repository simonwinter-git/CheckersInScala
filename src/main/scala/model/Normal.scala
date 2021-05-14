package model

case class Normal(row: Int, col: Int, color: String) extends Piece {
  override def state: String = "normal"

}
