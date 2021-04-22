import java.util
case class Field(id:(Int,Int)) {
  def state:Int = 0
  def fieldType:Boolean

  if ((id._1 - id._2) % 2 == 0) {
    fieldType = false
  } else {
    fieldType = true
  }

}