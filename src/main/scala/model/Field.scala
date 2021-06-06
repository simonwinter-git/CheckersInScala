package model

case class Field(pos: String, piece: Option[Piece]) {

  def isSet: Boolean = piece != None

  override def toString: String = piece match {
    case Some(s) => s.toString
    case None => " "
  }

  //def isWhite:Boolean = ((id._1 - id._2) % 2 == 0)
}