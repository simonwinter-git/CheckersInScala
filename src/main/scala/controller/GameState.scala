package controller


object GameState extends Enumeration {
  type GameState = Value
  val WHITE_TURN, BLACK_TURN = Value

  val map = Map[GameState, String] (
    WHITE_TURN -> "It's White's turn",
    BLACK_TURN -> "It's Black's turn"
  )

  def message(gameState: GameState) = {
    map(gameState)
  }

}


/*
trait GameState {
  def controller: Controller
  def move(start: String, dest: String): Unit
}

case class WhiteTurn(controller: Controller) extends GameState {
  override def move(start: String, dest: String): Unit = {
    if (controller.gameBoard.whiteMovePossible(start, dest)) {
      controller.gameBoard = controller.gameBoard.move(start, dest)
      controller.setGameState(BlackTurn(controller))
    }
  }
}

case class BlackTurn(controller: Controller) extends GameState {
  override def move(start: String, dest: String): Unit = {
    if (controller.gameBoard.blackMovePossible(start, dest)) {
      controller.gameBoard = controller.gameBoard.move(start, dest)
      controller.setGameState(WhiteTurn(controller))
    }
  }
}
*/


  /*
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
  */
