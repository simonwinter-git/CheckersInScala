package aview.gui

import java.awt.Color

import scala.swing._
import javax.swing.table._

import scala.swing.event._
import controller.controllerComponent.ControllerInterface
import controller.controllerComponent.controllerBaseImpl.Controller
import javax.swing.BorderFactory
import model.gameBoardComponent.gameBoardBaseImpl.Piece

class FieldPanel(row: Int, col: Int, controller: ControllerInterface, backgroundColor: Color) extends FlowPanel {

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

  val field: BoxPanel = new BoxPanel(Orientation.Horizontal) {
    label.text = fieldText()
    label.horizontalAlignment = Alignment.Right
    contents += label
    preferredSize = new Dimension(100, 100)
    background = backgroundColor
    if (color == "black") {
      border = BorderFactory.createEmptyBorder(0,50,40,0)
    } else border = BorderFactory.createEmptyBorder(-10,19,30,0) //this
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
