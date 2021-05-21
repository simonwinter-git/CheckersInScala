package controller
import model.GameBoard


trait GameState {
  def move(start: String, dest: String): GameBoard
}

class WhiteTurn(controller: Controller) extends GameState {
  override def move(start: String, dest: String): GameBoard = {
    if (controller.gameBoard.whiteMovePossible(start, dest)) {
      controller.gameBoard = controller.gameBoard.move(start, dest)
      controller.setGameState(new BlackTurn(controller))
    }
  }
}

class BlackTurn(controller: Controller) extends GameState {
  override def move(start: String, dest: String): GameBoard = {
    if (controller.gameBoard.blackMovePossible(start, dest)) {
      controller.gameBoard = controller.gameBoard.move(start, dest)
      controller.setGameState(new WhiteTurn(controller))
    }
  }
}

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
