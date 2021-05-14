package model

case class Field(pos: String, piece: Option[Piece]) {

  override def toString: String = pos.replace('0', 'X')

  //def isWhite:Boolean = ((id._1 - id._2) % 2 == 0)
}