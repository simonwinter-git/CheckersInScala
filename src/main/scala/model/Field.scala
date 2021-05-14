package model

case class Field(color: Int, state: Int) {

  override def toString: String = color.toString.replace('0', 'X')

  //def isWhite:Boolean = ((id._1 - id._2) % 2 == 0)
}