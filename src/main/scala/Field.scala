import java.util
case class Field(name:String, state:Boolean, fieldType:Boolean, id:(Int, Int)) {
  if ((id._1 - id._2) % 2 == 0) {
    fieldType = false

  }
}


