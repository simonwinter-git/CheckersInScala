package model
case class Field(state:Int) {
  override def toString: String = state.toString.replace('0', ' ')
  //def isWhite:Boolean = ((id._1 - id._2) % 2 == 0)
}