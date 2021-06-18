package model.gameBoardComponent.gameBoardBaseImpl
import model.gameBoardComponent.FieldInterface

case class Field (pos: String, piece: Option[Piece]) extends FieldInterface {

  def isSet: Boolean = piece != None

  override def toString: String = piece match {
    case Some(s) => s.toString
    case None => " "
  }

  //def isWhite:Boolean = ((id._1 - id._2) % 2 == 0)
}