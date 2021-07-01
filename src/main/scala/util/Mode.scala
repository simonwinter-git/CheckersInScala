package util

import model.playerComponent.Player

trait Mode {
  def timeOver(player: Player): Boolean
}