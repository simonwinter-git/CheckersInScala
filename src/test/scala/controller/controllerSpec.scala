package controller
import model.gameBoardComponent.gameBoardBaseImpl
import model.gameBoardComponent.gameBoardBaseImpl.{GameBoard, Piece}
import org.scalatest._
import controller.controllerComponent.GameState
import controller.controllerComponent.GameState.{BLACK_TURN, WHITE_TURN}
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec._
import util.Observer
class controllerSpec extends AnyWordSpec {
  "A Controller" should {
    val gb = new GameBoard(8)
    val controller = new Controller(gb)
    print(gb.posToStr(0,0).charAt(0).asDigit + "a")
    print(gb.posToStr(0,0).charAt(1).asDigit)
    "use undo/redo correctly" in {
      controller.gameBoard.field(0,0).isSet should be (false)
      controller.set(0,0, Piece.apply("normal", 0,0,"white"))
      controller.gameBoard.field(0,0).isSet should be (true)
      controller.isSet(0,0) should be (true)
      controller.undo
      controller.gameBoard.field(0,0).isSet should be (false)
      controller.redo
      controller.gameBoard.field(0,0).isSet should be (true)
    }
    "Set Correctly" in {
      controller.gameBoard.rowToInt("A2") should be (0)
      controller.gameBoard.colToInt("A2") should be (1)
      controller.gameBoard.getField("A2").toString should be (" ")
      controller.gameBoard.getField("A1").toString should be ("O")
    }
    "give the right size" in {
      controller.gameBoardSize should be (8)
    }
    "Give a Right Message about the next Turn" in {
      controller.statusText should be ("It's White's turn")
      controller.setGameState(BLACK_TURN)
      controller.statusText should be ("It's Black's turn")
    }
    "Should move right" in {
      controller.gameBoard.field(0,0).isSet should be (true)
      controller.move("A1", "B2")
      controller.gameBoard.getField("B2").toString should be (" ")
      controller.gameBoard.field(0,0).isSet should be (false)
      controller.gameBoard.field(1,1).isSet should be (true)
    }
  }
}