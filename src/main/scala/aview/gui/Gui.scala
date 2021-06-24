package aview.gui

import java.awt.Color

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import controller._
//import controller.controllerComponent.ControllerInterface
//import controller.controllerComponent.controllerBaseImpl.{GBSizeChanged}
import controller.controllerComponent.controllerBaseImpl.Controller
import controller.controllerComponent._
import javax.swing.BorderFactory

import scala.io.Source._

class Gui(controller: ControllerInterface) extends Frame {
  listenTo(controller)

  title = "Checkers"
  minimumSize = new Dimension(800, 800)
  var fields = Array.ofDim[FieldPanel](controller.gameBoardSize, controller.gameBoardSize)



  /*
  def highlightpanel = new FlowPanel {
    contents += new Label("Highlight:")
    for {index <- 0 to controller.gameBoardSize} {
      val button = Button(if (index == 0) "" else index.toString) {
        controller.highlight(index)
      }
    }
  }
 */

  def gameBoardPanel = new GridPanel(controller.gameBoardSize, controller.gameBoardSize) {
    for {
      row <- 0 until controller.gameBoardSize
      col <- 0 until controller.gameBoardSize
    } {
      val fieldPanel = new FieldPanel(row, col, controller, if((row+col)%2==0) new Color(255,255,255) else new Color(0,0,0))
      fields(row)(col) = fieldPanel
      contents += fieldPanel
    }
  }


/*
  def gameBoardPanel = new GridPanel(controller.gameBoardSize, controller.gameBoardSize) {
    def s: Int = controller.gameBoardSize
    for (i <- Range(0, scala.math.pow(s,2).toInt)) {
      val field = i % s
      val row = Integer2int((s-1) - i/s)

      val panel = new GridPanel(1, 1) {
        if (field % 2 == 1)
          if (row % 2 == 1) background = Color.WHITE
          else background = Color.BLACK
        else if (row % 2 == 0) background = Color.WHITE
        else background = Color.BLACK
        //minimumSize = new Dimension(50, 50)
      }
      contents += panel
    }
  }
*/
  def labelRow = new GridPanel(controller.gameBoardSize, 1) {
    for (i <- Range(1, controller.gameBoardSize + 1)) {
      contents += new Label {
        text = i.toString
        preferredSize = new Dimension(20, 40)
      }
    }
  }

  def labelCol = new GridPanel(1, 9) {
    border = BorderFactory.createEmptyBorder(0, 25, 0, 25)
    for (i <- Range(65, controller.gameBoardSize + 65)) {
      contents += new Label {
        horizontalAlignment = Alignment.Center

        text = i.toChar.toString
        preferredSize = new Dimension(40, 20)
      }
    }
  }
  /*
  def gameBoardPanel = new GridPanel(controller.gameBoardSize, controller.gameBoardSize) {
    border = LineBorder(java.awt.Color.BLACK, 2)
    background = java.awt.Color.BLACK
    for {
      outerRow <- 0 until controller.gameBoardSize
      outerColumn <- 0 until controller.gameBoardSize
    } {
      contents += new GridPanel(controller.gameBoardSize, controller.gameBoardSize) {
        border = LineBorder(java.awt.Color.BLACK, 2)
        for {
          innerRow <- 0 until controller.gameBoardSize
          innerColumn <- 0 until controller.gameBoardSize
        } {
          val x = outerRow * controller.gameBoardSize + innerRow
          val y = outerColumn * controller.gameBoardSize + innerColumn
          val fieldPanel = new FieldPanel(x, y, controller)
          fields(x)(y) = fieldPanel
          contents += fieldPanel
          listenTo(fieldPanel)
        }
      }
    }
  }
  */

  //val statusLine = new TextField(controller.statusText)

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

  /*
  def redraw = {
    fields = Array.ofDim[FieldPanel](controller.gameBoardSize, controller.gameBoardSize)
    contents = new BorderPanel {
      add(gameBoardPanel, BorderPanel.Position.Center)
      add(labelCol, BorderPanel.Position.North)
      add(labelCol, BorderPanel.Position.South)
      add(labelRow, BorderPanel.Position.East)
      add(labelRow, BorderPanel.Position.West)
    }
  }

   */
  def redraw = {
    for {
      row <- 0 until controller.gameBoardSize
      column <- 0 until controller.gameBoardSize
    } fields(row)(column).redraw
    //statusline.text = controller.statusText
    repaint
  }

}
