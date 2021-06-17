package controller
import model.gameBoardComponent.gameBoardBaseImpl
import model.gameBoardComponent.gameBoardBaseImpl.{Field, GameBoard, Piece}
import org.scalatest._
import controller.controllerComponent.GameState
import controller.controllerComponent.GameState.{BLACK_TURN, WHITE_TURN}
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec._
import util.Observer
class ControllerSpec extends AnyWordSpec {
  "A Controller" should {
    val gb = new GameBoard(3)
    val controller = new Controller(gb)
    "use undo/redo correctly" in {
      controller.gameBoard.field(0,0).isSet should be (false)
      controller.set(0,0, Piece.apply("normal", 0, 0, "white"))
      controller.set(1,1, Piece.apply("queen", 1, 1, "white"))
      controller.gameBoard.field(0,0).isSet should be (true)
      controller.isSet(0,0) should be (true)
      controller.undo
      controller.undo
      controller.gameBoard.field(0,0).isSet should be (false)
      controller.redo
      controller.redo
      controller.gameBoard.field(0,0).isSet should be (true)
    }
    "be set correctly" in {
      controller.gameBoard.rowToInt("A2") should be (0)
      controller.gameBoard.colToInt("A2") should be (1)
      controller.gameBoard.getField("A2").toString should be (" ")
      controller.gameBoard.getField("A1").toString should be ("O")
      controller.gameBoard.getField("B2").toString should be ("Q")
    }
    "have the right size" in {
      controller.gameBoardSize should be (3)
    }
    "deliver the right message about the next turn" in {
      controller.statusText should be ("It's White's turn")
      controller.setGameState(BLACK_TURN)
      controller.statusText should be ("It's Black's turn")
    }
    "move right" in {
      controller.gameBoard.field(0,0).isSet should be (true)
      controller.move("A1", "B2")
      controller.gameBoard.getField("A1").toString should be (" ")
      controller.gameBoard.field(0,0).isSet should be (false)
      controller.gameBoard.field(1,1).isSet should be (true)
      controller.gameBoard.getField("A2").toString should be (" ")
      controller.move("A2", "B3")
      controller.gameBoard.getField("A2").toString should be (" ")
      controller.gameBoard.getField("B3").toString should be (" ")
    }
    "have the correct String output" in {
      controller.gameBoardToString should be ("\n+-------+\n|       |\n|   O   |\n|       |\n+-------+\n")
    }
    "be able to resize the Gameboard" in {
      controller.resize(8)
      controller.gameBoardSize should be (8)
    }
  }
}