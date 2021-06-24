package aview
//import controller.controllerComponent.{ControllerInterface, GameState}
//import controller.controllerComponent.controllerBaseImpl.{FieldChanged, GBSizeChanged}
import controller.controllerComponent._

import scala.swing.Reactor
import scala.util.{Failure, Success, Try}

class Tui(controller: ControllerInterface) extends Reactor {

  listenTo(controller)

  def size = controller.gameBoardSize

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

  reactions += {
    case event: GBSizeChanged => printTui
    case event: FieldChanged => printTui
  }

  def printTui: Unit = {
    println("test")
    println(controller.gameBoardToString)
    println(GameState.message(controller.gameState))
  }

  //override def update: Unit = println(controller.gameBoardToString)
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