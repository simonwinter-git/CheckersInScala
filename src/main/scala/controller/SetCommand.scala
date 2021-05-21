package controller
import util.Command
import model.Piece

class SetCommand(row: Int, col: Int, piece: Piece, controller: Controller) extends Command {
  override def doStep: Unit = controller.gameBoard = controller.gameBoard.set(row, col, piece)

  override def undoStep: Unit = controller.gameBoard = controller.gameBoard.set(row, col, piece) // Vorherigen Zustand implementieren

  override def redoStep: Unit = controller.gameBoard = controller.gameBoard.set(row, col, piece)
}
