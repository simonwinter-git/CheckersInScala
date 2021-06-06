package aview.gui

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import controller._
import scala.io.Source._

class Gui(controller: Controller) extends Frame {
  listenTo(controller)

  title = "Checkers"
  var fields = Array.ofDim[FieldPanel](controller.gameBoardSize, controller.gameBoardSize)

  def
}
