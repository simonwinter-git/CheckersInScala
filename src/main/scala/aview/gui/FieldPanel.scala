package aview.gui

import scala.swing._
import javax.swing.table._

import scala.swing.event._
import controller.controllerComponent.controllerBaseImpl.Controller
import model.gameBoardComponent.gameBoardBaseImpl.Piece

class FieldPanel(row: Int, col: Int, controller: Controller) extends FlowPanel {

  def myField = controller.field(row, col)
  def cellText(): String = if (myField.isSet) controller.getPiece(row, col).get.toString








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

  def redraw = {
    contents.clear()
    label.text = fieldText(row, col)
    setBackground(cell)
    contents += cell
    repaint
  }

  def setBackground(p: Panel) = p.background = fieldColor

   */
}
