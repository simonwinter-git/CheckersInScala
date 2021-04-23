class Tui {
  def tuiEntry(input: String): Field = {
    input match {
      case "upleft" => Checkers.movement(-1,-1)
      case "upright" => Checkers.movement(-1,1)
      case "downleft" => Checkers.movement(1,-1)
      case "downright" => Checkers.movement(1,1)
    }
  }
}
