package util

import model.Player

trait Mode {
  def timeOver(player: Player): Boolean
}