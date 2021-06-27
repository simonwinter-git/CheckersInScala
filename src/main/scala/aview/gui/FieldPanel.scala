package aview.gui

import java.awt.Color

import scala.swing._
import javax.swing.table._

import scala.swing.event._
import controller.controllerComponent.ControllerInterface
import controller.controllerComponent.controllerBaseImpl.Controller
import javax.swing.BorderFactory
import model.gameBoardComponent.gameBoardBaseImpl.Piece

import scala.Checkers.{gui, injector}

class FieldPanel(row: Int, col: Int, controller: ControllerInterface, backgroundColor: Color) extends FlowPanel {
  vGap = 0
  hGap = 0
  var color: String = "white"
  def myField = controller.field(row, col)

  def fieldText(): String = {
    color = "white"
    if (myField.isSet) {
      if (controller.getPiece(row, col).get.getColor == "white") print("")
      controller.getPiece(row, col).get.toString
    } else " "
  }

  val label: Label =
    new Label {
      text = fieldText()
      font = new Font("Nocxyto Sans", 0, 72)
      //foreground = new Color(25, 100, 12)
    }

  val field: BoxPanel = new BoxPanel(Orientation.NoOrientation) {
    listenTo(mouse.clicks)
    reactions += {
      case e: MouseClicked =>
        if (gui.flagTest == 0) {
          if (myField.getPiece != None) {
            gui.fieldStart = myField.getPos
            gui.flagTest = 1
            print(gui.fieldStart + " Start\n")
            field.background = new Color(150, 150, 150)
          }
        } else {
          gui.flagTest = 0
          gui.fieldDest = myField.getPos
          print(gui.fieldDest + " Dest\n")
        }
        if (controller.movePossible(gui.fieldStart, gui.fieldDest).getBool) {
          print("move possible\n")
          if (!controller.movePossible(gui.fieldStart, gui.fieldDest).getRem.isBlank) controller.remove(controller.movePossible(gui.fieldStart, gui.fieldDest).getRem.charAt(1).toInt - 49, controller.movePossible(gui.fieldStart, gui.fieldDest).getRem.charAt(0).toInt - 65)
          controller.move(gui.fieldStart, gui.fieldDest)
        }
    }
    label.text = fieldText()
    label.horizontalAlignment = Alignment.Right
    contents += label
    preferredSize = new Dimension(100, 100)
    background = backgroundColor
    if (color == "black") {
      border = BorderFactory.createEmptyBorder(0,50,40,0)
    } else border = BorderFactory.createEmptyBorder(-5,19,30,0) //this
    repaint
  }


  def redraw = {
    contents.clear()
    label.text = fieldText()
    contents += field
    repaint
  }

  /*
  val givenFieldColor = new Color(200, 200, 255)
  val fieldColor = new Color(224, 224, 255)
  val highlightedFieldColor = new Color(192, 255, 192)
  def myField = controller.field(row, col)
  def fieldText(row: Int, col: Int) = if (controller.isSet(row, col)) " " + controller.field(row, col).piece.toString else " "
  val label =
    new Label {
      text = fieldText(row, col)
      font = new Font("Verdana", 1, 36)
    }
  val cell = new BoxPanel(Orientation.Vertical) {
    contents += label
    preferredSize = new Dimension (51, 51)
    background = givenFieldColor
    border = Swing.BeveledBorder(Swing.Raised)
  }
  def setBackground(p: Panel) = p.background = fieldColor
   */
}
