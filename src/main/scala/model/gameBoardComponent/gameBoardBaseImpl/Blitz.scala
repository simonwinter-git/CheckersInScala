package model.gameBoardComponent.gameBoardBaseImpl
import model.playerComponent.Player
import util.Mode

case class Blitz() extends Mode {
  override def timeOver(player: Player): Boolean = {
    if (player.timer == 0) {
      true
    } else
      false
  }
}
