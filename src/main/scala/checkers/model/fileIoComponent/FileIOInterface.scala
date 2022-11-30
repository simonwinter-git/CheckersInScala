package checkers.model.fileIoComponent
import checkers.model.gameBoardComponent.GameBoardInterface

trait FileIOInterface {
  def load: GameBoardInterface
  def save(gameBoard: GameBoardInterface): Unit
}
