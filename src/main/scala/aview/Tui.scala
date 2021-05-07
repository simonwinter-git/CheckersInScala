package aview
import controller.Controller
import util.Observer

class Tui(controller: Controller) extends Observer {

  controller.add(this)

  def tuiEntry(input: String): Unit = {
    input match {
      case "new8" => controller.createEmptyGameBoard(8)
      case "new10" => controller.createEmptyGameBoard(10)
    }
  }

  override def update: Unit = println(controller.gameBoardToString)
}