package model.gameBoardComponent.gameBoardBaseImpl
import model.playerComponent.Player
import util.Mode

case class Classic() extends Mode {
  override def timeOver(player: Player): Boolean = {
    false
  }
}
