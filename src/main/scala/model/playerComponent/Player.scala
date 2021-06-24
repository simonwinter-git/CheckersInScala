package model.playerComponent

case class Player(name: String, timer: Int, color: String) {
  override def toString: String = name
}