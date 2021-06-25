package model.fileIoComponent
import model.gameBoardComponent.GameBoardInterface

trait FileIOInterface {
  def load: GameBoardInterface
  def save(gameBoard: GameBoardInterface): Unit
}
