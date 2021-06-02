package model

case class Field(pos: String, piece: Option[Piece]) {

  override def toString: String = piece match {
    case Some(s) => s.toString
    case None => " "
  }

  //def isWhite:Boolean = ((id._1 - id._2) % 2 == 0)
}