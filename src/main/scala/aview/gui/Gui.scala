package aview.gui

import java.awt
import java.awt.{Color, Dimension, GridLayout, Toolkit}
import java.io.File

import scala.swing.{Button, _}
import scala.swing.Swing.LineBorder
import scala.swing.event._
import controller._
import javax.swing.{Icon, ImageIcon, JButton, JPanel}
import scalafx.application.JFXApp3
import scalafx.scene.shape.Rectangle
//import controller.controllerComponent.ControllerInterface
//import controller.controllerComponent.controllerBaseImpl.{GBSizeChanged}
import controller.controllerComponent.controllerBaseImpl.Controller
import controller.controllerComponent._
import javax.swing.BorderFactory
import scala.io.Source._
import scala.util.control.Breaks._

class Gui(controller: ControllerInterface) extends Frame {

  listenTo(controller)
  title = "Checkers"
  resizable = false
  minimumSize = new Dimension(800, 800)
  centerOnScreen
  var fields = Array.ofDim[FieldPanel](controller.gameBoardSize, controller.gameBoardSize)
  var flagTest = 0
  var fieldStart = ""
  var fieldDest = ""
  var colorFlag = new BoxPanel(Orientation.NoOrientation)

  val dir: String = new File("").getAbsolutePath
  iconImage = new ImageIcon(dir+"\\src\\main\\resources\\icon.png").getImage

  def gameBoardPanel = new GridPanel(controller.gameBoardSize, controller.gameBoardSize) {
    border = BorderFactory.createEmptyBorder(1, 1, 1, 1)
    for {
      row <- 0 until controller.gameBoardSize
      col <- 0 until controller.gameBoardSize
    } {
      val fieldPanel = new FieldPanel(row, col, controller, if((row+col)%2==0) new Color(255,255,255) else new Color(0,0,0))
      fields(row)(col) = fieldPanel
      contents += fieldPanel
    }
    background = new Color(40, 40, 40)

  }

  def labelRowL = new GridPanel(controller.gameBoardSize, 1) {
    if (controller.gameBoardSize == 10) border = BorderFactory.createEmptyBorder(0, 3, 0, 0)
    if (controller.gameBoardSize == 8) border = BorderFactory.createEmptyBorder(0, 2, 0, -1)
    background = new Color(40, 40, 40)
    for (i <- Range(1, controller.gameBoardSize + 1)) {
      contents += new Label {
        text = i.toString
        foreground = new Color(230, 230, 230)
        font = new Font("Arial", 1, 15)
        preferredSize = new Dimension(17, 0)
      }
    }
  }

  def labelRowR = new GridPanel(controller.gameBoardSize, 1) {
    if (controller.gameBoardSize == 10) border = BorderFactory.createEmptyBorder(0, -1, 0, 2)
    if (controller.gameBoardSize == 8) border = BorderFactory.createEmptyBorder(0, -1, 0, 2)
    background = new Color(40, 40, 40)
    for (i <- Range(1, controller.gameBoardSize + 1)) {
      contents += new Label {
        text = i.toString
        foreground = new Color(230, 230, 230)
        font = new Font("Arial", 1, 15)
        preferredSize = new Dimension(17, 0)
      }
    }
  }

  def labelColT = new GridPanel(1, controller.gameBoardSize - 1) {
    if (controller.gameBoardSize == 10) border = BorderFactory.createEmptyBorder(2, 20, 0, 20)
    if (controller.gameBoardSize == 8) border = BorderFactory.createEmptyBorder(2, 20, -2, 20)
    background = new Color(40, 40, 40)
    for (i <- Range(65, controller.gameBoardSize + 65)) {
      contents += new Label {
        //horizontalAlignment = Alignment.Center
        text = i.toChar.toString
        foreground = new Color(230, 230, 230)
        font = new Font("Arial", 1, 15)
        preferredSize = new Dimension(0, 17)
      }
    }
  }

  def labelColB = new GridPanel(1, controller.gameBoardSize - 1) {
    if (controller.gameBoardSize == 10) border = BorderFactory.createEmptyBorder(0, 20, 0, 20)
    if (controller.gameBoardSize == 8) border = BorderFactory.createEmptyBorder(-1, 20, 0, 20)
    background = new Color(40, 40, 40)
    for (i <- Range(65, controller.gameBoardSize + 65)) {
      contents += new Label {
        //horizontalAlignment = Alignment.Center
        text = i.toChar.toString
        foreground = new Color(230, 230, 230)
        font = new Font("Arial", 1, 15)
        preferredSize = new Dimension(0, 17)
      }
    }
  }

  contents = new BorderPanel {
    //add(gameBoardPanel, BorderPanel.Position.Center)
    add(gameBoardPanel, BorderPanel.Position.Center)
    add(labelColT, BorderPanel.Position.North)
    add(labelColB, BorderPanel.Position.South)
    add(labelRowR, BorderPanel.Position.East)
    add(labelRowL, BorderPanel.Position.West)
    //add(statusLine, BorderPanel.Position.South)
  }

  menuBar = new MenuBar {
    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("New") { controller.createGameBoard(controller.gameBoardSize) })
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
      add(labelColT, BorderPanel.Position.North)
      add(labelColB, BorderPanel.Position.South)
      add(labelRowR, BorderPanel.Position.East)
      add(labelRowL, BorderPanel.Position.West)
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