package scala
import com.google.inject.Guice
import aview.Tui
//import aview.gui.Gui
import controller.controllerComponent.ControllerInterface

import scala.io.StdIn.readLine

object Checkers {
  val injector = Guice.createInjector(new CheckersModule)
  val controller = injector.getInstance(classOf[ControllerInterface])
  val tui = new Tui(controller)
  //val gui = new Gui(controller)
  controller.createGameBoard(8)

  def main(args: Array[String]): Unit = {
    var input: String = ""
    do {
      input = readLine()
      tui.tuiEntry(input)
    } while (input != "quit")
  }
}