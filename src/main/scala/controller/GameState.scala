package controller

object GameState extends Enumeration {
  type GameState = Value
  val IDLE, WHITE, BLACK = Value

  val map = Map[GameState, String] (
    IDLE -> "",
    WHITE -> "It's White's turn",
    BLACK -> "It's Black's turn"
  )

  def message(gameState: GameState) = {
    map(gameState)
  }
}
