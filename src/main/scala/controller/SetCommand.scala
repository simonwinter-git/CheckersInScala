package controller
import util.Command
import model.{GameBoard}

class SetCommand(start: String, dest: String, controller: Controller) extends Command {

  var memento: (GameBoard, GameState) = (
    controller.gameBoard,
    controller.gameState
  )

  override def doStep: Unit = {
    memento = (controller.gameBoard, controller.gameState)
    controller.gameBoard = controller.gameBoard.move(start, dest)
  }

  override def undoStep: Unit = {
    val newMemento = (controller.gameBoard, controller.gameState)
    controller.gameBoard = memento._1
    controller.gameState = memento._2
    memento = newMemento
    controller.notifyObservers
  }

  override def redoStep: Unit = {
    undoStep
  }
}
