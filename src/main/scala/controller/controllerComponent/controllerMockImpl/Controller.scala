package controller.controllerComponent.controllerMockImpl
import controller.controllerComponent.GameState
import controller.controllerComponent.GameState.{GameState, WHITE_TURN}
import controller.controllerComponent.ControllerInterface
import model.gameBoardComponent.{FieldInterface, GameBoardInterface, PieceInterface}
import model.gameBoardComponent.gameBoardMockImpl.GameBoard
import model.gameBoardComponent.gameBoardBaseImpl.Piece

class Controller(var gameBoard: GameBoardInterface) extends ControllerInterface {

  gameBoard = new GameBoard(1)

  override def gameBoardSize: Int = 1

  override def createEmptyGameBoard(size: Int): Unit = {}

  override def createNewGameBoard: Unit = {}

  override def createGameBoard(size: Int): Unit = {}

  override def save: Unit = {}

  override def load: Unit = {}

  override def undo: Unit = {}

  override def redo: Unit = {}

  override def resize(newSize: Int): Unit = {}

  override def field(row: Int, col: Int): FieldInterface = gameBoard.field(row, col)

  override def set(row: Int, col: Int, piece: Piece): Unit = {}

  override def isSet(row: Int, col: Int): Boolean = false

  override def getPiece(row: Int, col: Int): Option[PieceInterface] = None

  override def gameBoardToString: String = gameBoard.toString

  override def gameState: GameState = WHITE_TURN

  override def statusText: String = GameState.message(gameState)

  override def move(start: String, dest: String): Unit = {}

  override def movePossible(start: String, dest: String): Boolean = true

  override def setGameState(newGameState: GameState): Unit = {}
}
