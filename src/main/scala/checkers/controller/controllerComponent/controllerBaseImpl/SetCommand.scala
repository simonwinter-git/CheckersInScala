package checkers.controller.controllerComponent.controllerBaseImpl

import checkers.controller.controllerComponent.GameState.GameState
import checkers.model.gameBoardComponent.GameBoardInterface
import checkers.util.Command
import checkers.model.gameBoardComponent.gameBoardBaseImpl.{GameBoard, Piece}

class SetCommand(start: Int, dest: Int, piece: Option[Piece], controller: Controller) extends Command {

  var memento: (GameBoardInterface, GameState) = (
    controller.gameBoard,
    controller.gameState
  )

  override def doStep: Unit = {
    memento = (controller.gameBoard, controller.gameState)
    controller.gameBoard = controller.gameBoard.set(start, dest, piece)
  }

  override def undoStep: Unit = {
    val newMemento = (controller.gameBoard, controller.gameState)
    controller.gameBoard = memento._1
    controller.gameState = memento._2
    memento = newMemento
  }

  override def redoStep: Unit = {
    undoStep
  }
}
