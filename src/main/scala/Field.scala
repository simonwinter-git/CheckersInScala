import java.util

case class Field(id:(Int,Int)) {
  def state:Int = 0
  def isWhite:Boolean = ((id._1 - id._2) % 2 == 0)
}