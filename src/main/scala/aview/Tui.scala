package aview
import controller.Controller
import util.Observer

import scala.util.{Try, Success, Failure}

class Tui(controller: Controller) extends Observer {

  controller.add(this)

  def tuiEntry(input: String): Unit = {
    def args:Array[String] = input.split(" ")
    args(0) match {
      case "new" =>
        Try {args(1).toInt} match {
          case Failure(e) => println(e.getMessage + "\nTry an Integer")
          case Success(e) => controller.createGameBoard(args(1).toInt)
        }
    }
  }


  override def update: Unit = println(controller.gameBoardToString)
}


/*
def tuiEntry(input: String): Unit = {
  input match {
  case "new8" => controller.createEmptyGameBoard(8)
  case "new10" => controller.createEmptyGameBoard(10)
  case "a6tob5" => controller.
}
}

 */