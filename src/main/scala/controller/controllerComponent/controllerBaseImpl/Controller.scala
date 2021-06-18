package controller.controllerComponent.controllerBaseImpl
import com.google.inject.name.Names
import com.google.inject.{Guice, Inject}
import net.codingwell.scalaguice.InjectorExtensions._
import controller.controllerComponent.GameState._
import controller.controllerComponent.{ControllerInterface, FieldChanged, GBSizeChanged, GameState}
import model.gameBoardComponent.{FieldInterface, GameBoardInterface}
import model.gameBoardComponent.gameBoardBaseImpl.{Field, GameBoard, GameBoardCreator, Piece}
import util.UndoManager

import scala.swing.Publisher

class Controller @Inject() (var gameBoard: GameBoardInterface) extends ControllerInterface with Publisher {

  private val undoManager = new UndoManager
  var gameState: GameState = WHITE_TURN
  val injector = Guice.createInjector(new CheckersModule)

  def createEmptyGameBoard(size: Int): Unit = {
    size match {
      case 8 => gameBoard = injector.instance[GameBoardInterface](Names.named("8"))
      case 10 => gameBoard = injector.instance[GameBoardInterface](Names.named("10"))
      case _ =>
    }
    publish(new FieldChanged)
  }

  def resize(newSize: Int): Int = {
    newSize match {
      case 8 => gameBoard = injector.instance[GameBoardInterface](Names.named("8"))
      case 10 => gameBoard = injector.instance[GameBoardInterface](Names.named("10"))
      case _ =>
    }
    publish(new GBSizeChanged(newSize))
    newSize
  }

  def createGameBoard(size: Int): Unit = {
    gameBoard = new GameBoardCreator(size).createBoard()
    //gameState = NEW
    publish(new FieldChanged)
  }

  def setGameState(newGameState: GameState): Unit = {
    gameState = newGameState
  }

  def gameBoardToString: String = gameBoard.toString

  def set(row: Int, col: Int, piece: Piece): Unit = {
    undoManager.doStep(new SetCommand(row, col, piece, this))
    gameBoard = gameBoard.set(row, col, piece)
    publish(new FieldChanged)
  }

  def move(start: String, dest: String): Unit = {
    undoManager.doStep(new MoveCommand(start, dest, this))
    gameBoard = gameBoard.move(start, dest)
    publish(new FieldChanged)
  }

  def undo: Unit = {
    undoManager.undoStep
    publish(new FieldChanged)
  }

  def redo: Unit = {
    undoManager.redoStep
    publish(new FieldChanged)
  }

  def isSet(row: Int, col: Int): Boolean = gameBoard.field(row, col).isSet

  def field(row: Int, col: Int):FieldInterface = gameBoard.field(row,col)

  def gameBoardSize: Int = gameBoard.size

  def statusText: String = GameState.message(gameState)
  /*
  def highlight(index: Int): Unit = {
    gameBoard = gameBoard.highlight(index)
    publish(new FieldChanged)
  }
  */

}