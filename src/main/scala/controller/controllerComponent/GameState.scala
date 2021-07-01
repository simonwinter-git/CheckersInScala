package controller.controllerComponent

object GameState extends Enumeration {
  type GameState = Value
  var s: String = ""
  val WHITE_TURN, BLACK_TURN, WHITE_CAP, BLACK_CAP, START, WHITE_WON, BLACK_WON= Value

  val map = Map[GameState, String] (
    WHITE_TURN -> "It's White's turn",
    BLACK_TURN -> "It's Black's turn",
    WHITE_CAP -> "It's White's cap turn",
    BLACK_CAP -> "It's Black's cap turn",
    START -> "Start",
    WHITE_WON -> "The game is over, White has won",
    BLACK_WON -> "The game is over, Black has won"
  )
  def message(gameState: GameState) = {
    map(gameState)
  }
}
