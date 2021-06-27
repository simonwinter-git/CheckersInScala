package aview.gui

import java.awt.Color

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import controller._
import scalafx.application.JFXApp3
import scalafx.scene.shape.Rectangle
//import controller.controllerComponent.ControllerInterface
//import controller.controllerComponent.controllerBaseImpl.{GBSizeChanged}
import controller.controllerComponent.controllerBaseImpl.Controller
import controller.controllerComponent._
import javax.swing.BorderFactory
/*import scalafx.scene._
import scalafx.Includes._
import scalafx.application.JFXApp3
import scalafx.scene.input.MouseEvent */
import scala.io.Source._

class Gui(controller: ControllerInterface) extends Frame {
  listenTo(controller)

  title = "Checkers"
  resizable = false
  minimumSize = new Dimension(800, 800)
  var fields = Array.ofDim[FieldPanel](controller.gameBoardSize, controller.gameBoardSize)
  var flagTest = 0
  var fieldStart = ""
  var fieldDest = ""


  def gameBoardPanel = new GridPanel(controller.gameBoardSize, controller.gameBoardSize) {



    /*listenTo(mouse.clicks)
    var flagTest = 0
    var fieldStart = ""
    var fieldDest = ""
    reactions += {
      case e: MouseClicked =>
        if (flagTest == 0) {
          val pX = (e.point.x / 75).toInt
          val pY = (e.point.y / 75).toInt
          fieldStart = (pX + 65).toChar.toString + (pY + 1).toString
          flagTest = 1
          print("t")
        } else {
          val pX2 = (e.point.x / 75).toInt
          val pY2 = (e.point.y / 75).toInt
          print("p")
          fieldDest = (pX2 + 65).toChar.toString + (pY2 + 1).toString
          flagTest = 0
        }
        if (controller.movePossible(fieldStart, fieldDest).getBool) {
          if (!controller.movePossible(fieldStart, fieldDest).getRem.isBlank) controller.remove(controller.movePossible(fieldStart, fieldDest).getRem.charAt(1).toInt - 49, controller.movePossible(fieldStart, fieldDest).getRem.charAt(0).toInt - 65)
          controller.move(fieldStart, fieldDest)
        }
    } */
    for {
      row <- 0 until controller.gameBoardSize
      col <- 0 until controller.gameBoardSize
    } {
      val fieldPanel = new FieldPanel(row, col, controller, if((row+col)%2==0) new Color(255,255,255) else new Color(0,0,0))
      fields(row)(col) = fieldPanel
      contents += fieldPanel
    }

  }


  def labelRow = new GridPanel(controller.gameBoardSize, 1) {
    for (i <- Range(1, controller.gameBoardSize + 1)) {
      contents += new Label {
        text = i.toString
        preferredSize = new Dimension(20, 0)
      }
    }
  }


  def labelCol = new GridPanel(1, 9) {
    border = BorderFactory.createEmptyBorder(0, 25, 0, 25)
    for (i <- Range(65, controller.gameBoardSize + 65)) {
      contents += new Label {
        horizontalAlignment = Alignment.Center

        text = i.toChar.toString
        preferredSize = new Dimension(0, 20)
      }
    }
  }


  contents = new BorderPanel {
    add(gameBoardPanel, BorderPanel.Position.Center)
    add(labelCol, BorderPanel.Position.North)
    add(labelCol, BorderPanel.Position.South)
    add(labelRow, BorderPanel.Position.East)
    add(labelRow, BorderPanel.Position.West)
    //add(statusLine, BorderPanel.Position.South)
  }

  menuBar = new MenuBar {
    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("New") { controller.createEmptyGameBoard(controller.gameBoardSize) })
      contents += new MenuItem(Action("Quit") { System.exit(0) })
    }
    contents += new Menu("Edit") {
      mnemonic = Key.E
      contents += new MenuItem(Action("Undo") { controller.undo })
      contents += new MenuItem(Action("Redo") { controller.redo })
    }
    contents += new Menu("Options") {
      mnemonic = Key.O
      contents += new MenuItem(Action("Size 8*8") { controller.resize(8) })
      contents += new MenuItem(Action("Size 10*10") { controller.resize(10) })
    }
  }

  visible = true
  redraw

  reactions += {
    case event: GBSizeChanged => resize(event.newSize)
    case event: FieldChanged => redraw
  }


  def resize(gameBoardSize: Int) = {
    fields = Array.ofDim[FieldPanel](controller.gameBoardSize, controller.gameBoardSize)
    contents = new BorderPanel {
      add(gameBoardPanel, BorderPanel.Position.Center)
      add(labelCol, BorderPanel.Position.North)
      add(labelCol, BorderPanel.Position.South)
      add(labelRow, BorderPanel.Position.East)
      add(labelRow, BorderPanel.Position.West)
    }
  }


  def redraw = {
    for {
      row <- 0 until controller.gameBoardSize
      column <- 0 until controller.gameBoardSize
    } fields(row)(column).redraw
    //statusline.text = controller.statusText
    repaint
    visible = true
  }
}

