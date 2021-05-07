package model
case class Player(name:String, checker:Int, color:String) {
  override def toString:String = name
}
